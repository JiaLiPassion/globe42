package org.globe42.web.tasks;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;

import org.globe42.dao.PersonDao;
import org.globe42.dao.TaskDao;
import org.globe42.dao.UserDao;
import org.globe42.domain.Person;
import org.globe42.domain.User;
import org.globe42.web.security.CurrentUser;
import org.globe42.web.util.PageDTO;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for tasks
 * @author JB Nizet
 */
@RestController
@RequestMapping(value = "/api/tasks")
@Transactional
public class TaskController {

    static final int PAGE_SIZE = 20;

    private final TaskDao taskDao;
    private final UserDao userDao;
    private final PersonDao personDao;
    private final CurrentUser currentUser;

    public TaskController(TaskDao taskDao,
                          UserDao userDao,
                          PersonDao personDao,
                          CurrentUser currentUser) {
        this.taskDao = taskDao;
        this.userDao = userDao;
        this.personDao = personDao;
        this.currentUser = currentUser;
    }

    @GetMapping
    public List<TaskDTO> listTodo() {
        return taskDao.findTodo().stream().map(TaskDTO::new).collect(Collectors.toList());
    }

    @GetMapping(params = "mine")
    public List<TaskDTO> listMine() {
        User user = userDao.getOne(currentUser.getUserId());
        return taskDao.findTodoByAssignee(user).stream().map(TaskDTO::new).collect(Collectors.toList());
    }

    @GetMapping(params = "unassigned")
    public List<TaskDTO> listUnassigned() {
        return taskDao.findTodoUnassigned().stream().map(TaskDTO::new).collect(Collectors.toList());
    }

    @GetMapping(params = "person")
    public List<TaskDTO> listForPerson(@RequestParam("person") Long personId) {
        Person person = personDao.getOne(personId);
        return taskDao.findTodoByConcernedPerson(person).stream().map(TaskDTO::new).collect(Collectors.toList());
    }

    @GetMapping(params = "before")
    public List<TaskDTO> listTodoBefore(@RequestParam("before") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return taskDao.findTodoBefore(date).stream().map(TaskDTO::new).collect(Collectors.toList());
    }

    @GetMapping(params = "archived")
    public PageDTO<TaskDTO> listArchived(@RequestParam Optional<Integer> page) {
        return PageDTO.fromPage(taskDao.findArchived(PageRequest.of(page.orElse(0), PAGE_SIZE)), TaskDTO::new);
    }
}