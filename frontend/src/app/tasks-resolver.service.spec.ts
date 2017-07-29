import { TestBed, inject } from '@angular/core/testing';

import { TasksResolverService } from './tasks-resolver.service';
import { TaskService } from './task.service';
import { ActivatedRouteSnapshot } from '@angular/router';
import { Observable } from 'rxjs/Observable';
import { HttpClientModule } from '@angular/common/http';
import { NowService } from './now.service';

describe('TasksResolverService', () => {
  let resolver: TasksResolverService;
  let taskService: TaskService;

  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [NowService, TaskService, TasksResolverService],
      imports: [HttpClientModule]
    });

    resolver = TestBed.get(TasksResolverService);
    taskService = TestBed.get(TaskService);
  });

  function routeWithType(taskListType: string): ActivatedRouteSnapshot {
    const result: any = {
      data: {
        taskListType
      }
    };
    return result;
  }

  it('should resolve tasks for todo list type', () => {
    const route = routeWithType('todo');
    const expected = Observable.of([]);
    spyOn(taskService, 'listTodo').and.returnValue(expected);
    expect(resolver.resolve(route)).toBe(expected);
  });

  it('should resolve tasks for mine list type', () => {
    const route = routeWithType('mine');
    const expected = Observable.of([]);
    spyOn(taskService, 'listMine').and.returnValue(expected);
    expect(resolver.resolve(route)).toBe(expected);
  });

  it('should resolve tasks for urgent list type', () => {
    const route = routeWithType('urgent');
    const expected = Observable.of([]);
    spyOn(taskService, 'listUrgent').and.returnValue(expected);
    expect(resolver.resolve(route)).toBe(expected);
  });

  it('should resolve tasks for unassigned list type', () => {
    const route = routeWithType('unassigned');
    const expected = Observable.of([]);
    spyOn(taskService, 'listUnassigned').and.returnValue(expected);
    expect(resolver.resolve(route)).toBe(expected);
  });

  it('should resolve tasks for person list type', () => {
    const route: any = routeWithType('person');
    route.parent = {
      data: {
        person: {
          id: 42
        }
      }
    };
    const expected = Observable.of([]);
    spyOn(taskService, 'listForPerson').and.returnValue(expected);
    expect(resolver.resolve(route)).toBe(expected);
    expect(taskService.listForPerson).toHaveBeenCalledWith(42);
  });

  it('should throw when unknown list type', () => {
    const route = routeWithType('unknown');
    try {
      resolver.resolve(route);
      fail('should have thrown');
    } catch (e) {
      expect(e.message).toBeTruthy();
    }
  });
});
