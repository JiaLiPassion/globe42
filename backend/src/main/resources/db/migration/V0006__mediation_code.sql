CREATE SEQUENCE mediation_code_a_seq START WITH 1;
CREATE SEQUENCE mediation_code_b_seq START WITH 1;
CREATE SEQUENCE mediation_code_c_seq START WITH 1;
CREATE SEQUENCE mediation_code_d_seq START WITH 1;
CREATE SEQUENCE mediation_code_e_seq START WITH 1;
CREATE SEQUENCE mediation_code_f_seq START WITH 1;
CREATE SEQUENCE mediation_code_g_seq START WITH 1;
CREATE SEQUENCE mediation_code_h_seq START WITH 1;
CREATE SEQUENCE mediation_code_i_seq START WITH 1;
CREATE SEQUENCE mediation_code_j_seq START WITH 1;
CREATE SEQUENCE mediation_code_k_seq START WITH 1;
CREATE SEQUENCE mediation_code_l_seq START WITH 1;
CREATE SEQUENCE mediation_code_m_seq START WITH 1;
CREATE SEQUENCE mediation_code_n_seq START WITH 1;
CREATE SEQUENCE mediation_code_o_seq START WITH 1;
CREATE SEQUENCE mediation_code_p_seq START WITH 1;
CREATE SEQUENCE mediation_code_q_seq START WITH 1;
CREATE SEQUENCE mediation_code_r_seq START WITH 1;
CREATE SEQUENCE mediation_code_s_seq START WITH 1;
CREATE SEQUENCE mediation_code_t_seq START WITH 1;
CREATE SEQUENCE mediation_code_u_seq START WITH 1;
CREATE SEQUENCE mediation_code_v_seq START WITH 1;
CREATE SEQUENCE mediation_code_w_seq START WITH 1;
CREATE SEQUENCE mediation_code_x_seq START WITH 1;
CREATE SEQUENCE mediation_code_y_seq START WITH 1;
CREATE SEQUENCE mediation_code_z_seq START WITH 1;

ALTER TABLE person DROP CONSTRAINT person_nick_name_key;
ALTER TABLE person ALTER nick_name DROP NOT NULL;
ALTER TABLE person ALTER last_name SET NOT NULL;
ALTER TABLE person ALTER mediation_code SET NOT NULL;
ALTER TABLE person ADD CONSTRAINT person_un1 UNIQUE (mediation_code);
