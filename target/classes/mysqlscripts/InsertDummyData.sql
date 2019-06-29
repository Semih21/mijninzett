INSERT INTO cohort VALUES (12,'2017-01-01','2017-06-01');
INSERT INTO cohort VALUES (13,'2017-08-01','2018-01-01');
INSERT INTO cohort VALUES (14,'2017-11-01','2018-04-01');
INSERT INTO cohort VALUES (15,'2018-02-01','2018-07-01');
INSERT INTO cohort VALUES (16,'2019-05-01','2019-10-01');
INSERT INTO cohort VALUES (17,'2019-08-01','2020-01-01');


INSERT INTO task_application VALUES (1,'2017-01-02',2,'Docent',null,2);
INSERT INTO task_application VALUES (2,'2017-01-02',2,'Docent',null,6);
INSERT INTO task_application VALUES (3,'2017-01-02',2,'Docent',null,10);
INSERT INTO task_application VALUES (1,'2017-01-02',2,'Docent',null,12);
INSERT INTO task_application VALUES (2,'2017-01-02',2,'Docent',null,13);
INSERT INTO task_application VALUES (3,'2017-01-02',2,'Docent',null,14);
INSERT INTO task_application VALUES (1,'2017-02-02',2,'Docent',null,15);
INSERT INTO task_application VALUES (2,'2017-03-02',2,'Docent',null,16);
INSERT INTO task_application VALUES (3,'2017-04-02',2,'Docent',null,17);


INSERT INTO Task (task_Id,task_Name,estimated_Hours,years_To_Expiry_Date) VALUES (1,'Voorzitter Examencommissie',32,3);
INSERT INTO Task (task_Id,task_Name,estimated_Hours,years_To_Expiry_Date) VALUES (2,'Lid BibliotheekCommissie',16,3);
INSERT INTO Task (task_Id,task_Name,estimated_Hours,years_To_Expiry_Date) VALUES (3,'Begeleiding',8,3);

INSERT INTO task (task_id,estimated_hours,task_name,years_to_expiry_date) VALUES (12,32,'niet_onderwijstaak12',3);
INSERT INTO task (task_id,estimated_hours,task_name,years_to_expiry_date) VALUES (13,30,'niet_onderwijstaak13',3);
INSERT INTO task (task_id,estimated_hours,task_name,years_to_expiry_date) VALUES (14,13,'niet_onderwijstaak14',3);
INSERT INTO task (task_id,estimated_hours,task_name,years_to_expiry_date) VALUES (15,23,'niet_onderwijstaak15',3);
INSERT INTO task (task_id,estimated_hours,task_name,years_to_expiry_date) VALUES (16,43,'niet_onderwijstaak16',3);
INSERT INTO task (task_id,estimated_hours,task_name,years_to_expiry_date) VALUES (17,6,'niet_onderwijstaak17',3);
INSERT INTO task (task_id,estimated_hours,task_name,years_to_expiry_date) VALUES (18,12,'niet_onderwijstaak18',3);
INSERT INTO task (task_id,estimated_hours,task_name,years_to_expiry_date) VALUES (19,9,'niet_onderwijstaak19',3);
INSERT INTO task (task_id,estimated_hours,task_name,years_to_expiry_date) VALUES (20,10,'niet_onderwijstaak20',3);
INSERT INTO task (task_id,estimated_hours,task_name,years_to_expiry_date) VALUES (21,11,'niet_onderwijstaak21',3);
INSERT INTO task (task_id,estimated_hours,task_name,years_to_expiry_date) VALUES (22,13,'niet_onderwijstaak22',3);
INSERT INTO task (task_id,estimated_hours,task_name,years_to_expiry_date) VALUES (23,15,'niet_onderwijstaak23',3);
INSERT INTO task (task_id,estimated_hours,task_name,years_to_expiry_date) VALUES (24,17,'niet_onderwijstaak24',3);
INSERT INTO task (task_id,estimated_hours,task_name,years_to_expiry_date) VALUES (25,19,'niet_onderwijstaak25',3);
INSERT INTO task (task_id,estimated_hours,task_name,years_to_expiry_date) VALUES (26,21,'niet_onderwijstaak26',3);
INSERT INTO task (task_id,estimated_hours,task_name,years_to_expiry_date) VALUES (27,23,'niet_onderwijstaak27',3);
INSERT INTO task (task_id,estimated_hours,task_name,years_to_expiry_date) VALUES (28,25,'niet_onderwijstaak28',3);

INSERT INTO `mijn_inzet`.`subject` (`subject_id`, `estimated_hours`, `subject_name`, `years_to_expiry_date`) VALUES ('1', '20', 'Programming', '2');
INSERT INTO `mijn_inzet`.`subject` (`subject_id`, `estimated_hours`, `subject_name`, `years_to_expiry_date`) VALUES ('2', '30', 'OOP', '2');
INSERT INTO `mijn_inzet`.`subject` (`subject_id`, `estimated_hours`, `subject_name`, `years_to_expiry_date`) VALUES ('3', '20', 'Databases', '2');
INSERT INTO subject  VALUES ('4', '20', 'ADS1', '2');
INSERT INTO subject  VALUES ('5', '20', 'ADS2', '2');
INSERT INTO subject  VALUES ('6', '20', 'ADS3', '2');
INSERT INTO subject  VALUES ('7', '20', 'WEBApplications1', '2');
INSERT INTO subject  VALUES ('8', '20', 'WEBApplications2', '2');
INSERT INTO subject  VALUES ('9', '20', 'WEBApplications3', '2');
INSERT INTO subject  VALUES ('10', '20', 'Javascript', '2');
INSERT INTO subject  VALUES ('11', '20', 'AJAX', '2');
INSERT INTO subject  VALUES ('12', '20', 'Thymeleaf', '2');



INSERT INTO roleName (role_id, role_name, role_desc) VALUES (1,'ADMIN','This user has admin rights for administrative work');
INSERT INTO roleName (role_id, role_name, role_desc) VALUES (2,'TEACHER','teacher');
INSERT INTO roleName (role_id, role_name, role_desc) VALUES (3,'COORDINATOR','coordinator');
INSERT INTO roleName (role_id, role_name, role_desc) VALUES (4,'SCHEDULER','This user has admin rights for administrative work');
INSERT INTO roleName (role_id, role_name, role_desc) VALUES (5,'MANAGER','This user has admin rights for administrative work');

insert into user (user_id,first_name,last_name,email,password,status) values (1,'hans','zuidervaart','admin@gmail.com','$2a$10$DD/FQ0hTIprg3fGarZl1reK1f7tzgM4RuFKjAKyun0Si60w6g3v5i','VERIFIED');
insert into user_role (user_id, role_id) values ('1','1');

insert into user(roleName,id,password,username) values ("teacher",2,"1","twee");
insert into staff_availability(cohort,color_option,day,day_part,user_id) values ("15","ROOD","Maandag","Ochtend",1);
insert into staff_availability (cohort,color_option,day,day_part,user_id) values ("15","GEEL","Maandag","Middag",1);
insert into staff_availability (cohort,color_option,day,day_part,user_id) values ("15","GROEN","Maandag","Avond",1);
insert into staff_availability (cohort,color_option,day,day_part,user_id) values ("15","GROEN","Dinsdag","Ochtend",1);
insert into staff_availability (cohort,color_option,day,day_part,user_id) values ("15","ROOD","Dinsdag","Middag",1);
insert into staff_availability (cohort,color_option,day,day_part,user_id) values ("15","GEEL","Dinsdag","Avond",1);
insert into staff_availability (cohort,color_option,day,day_part,user_id) values ("15","ROOD","Woensdag","Ochtend",1);
insert into staff_availability (cohort,color_option,day,day_part,user_id) values ("15","ROOD","Woensdag","Middag",1);
insert into staff_availability (cohort,color_option,day,day_part,user_id) values ("15","GEEL","Woensdag","Avond",1);
insert into staff_availability (cohort,color_option,day,day_part,user_id) values ("15","GROEN","Donderdag","Ochtend",1);
insert into staff_availability (cohort,color_option,day,day_part,user_id) values ("15","GROEN","Donderdag","Middag",1);
insert into staff_availability (cohort,color_option,day,day_part,user_id) values ("15","GROEN","Donderdag","Avond",1);
insert into staff_availability (cohort,color_option,day,day_part,user_id) values ("15","ROOD","Vrijdag","Ochtend",1);
insert into staff_availability (cohort,color_option,day,day_part,user_id) values ("15","ROOD","Vrijdag","Middag",1);
insert into staff_availability (cohort,color_option,day,day_part,user_id) values ("15","ROOD","Vrijdag","Avond",1);
insert into staff_availability (cohort,color_option,day,day_part,user_id) values ("16","ROOD","Woensdag","Middag",1);
insert into staff_availability (cohort,color_option,day,day_part,user_id) values ("16","GEEL","Woensdag","Avond",1);
insert into staff_availability (cohort,color_option,day,day_part,user_id) values ("16","GROEN","Donderdag","Ochtend",1);
insert into staff_availability (cohort,color_option,day,day_part,user_id) values ("16","GROEN","Donderdag","Middag",1);
insert into staff_availability (cohort,color_option,day,day_part,user_id) values ("16","GROEN","Donderdag","Avond",1);
insert into staff_availability (cohort,color_option,day,day_part,user_id) values ("16","ROOD","Vrijdag","Ochtend",1);
insert into staff_availability (cohort,color_option,day,day_part,user_id) values ("16","ROOD","Vrijdag","Middag",1);
insert into staff_availability (cohort,color_option,day,day_part,user_id) values ("16","ROOD","Vrijdag","Avond",1);

