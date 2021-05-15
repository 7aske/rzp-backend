use `rzp-database`;

insert into `role` (`role_id`, `name`)
values (1, 'ADMIN');
insert into `role` (`role_id`, `name`)
values (2, 'AUTHOR');
insert into `role` (`role_id`, `name`)
values (3, 'USER');

insert into `user` (`user_id`, `username`, `password`, `email`, `first_name`, `last_name`, `about`, `display_name`)
values (1, 'admin', '$2a$10$d6.rCRuPmri4fTy2vpItIeo49MPPFS/5tmorQASLfLawEGOip0Du2', 'admin@example.com', 'Ad', 'Min', 'Administrator', 'admin');

insert into `user_role` (`role_fk`, `user_fk`)
values (1, 1);

insert into `category` (`category_id`, `name`) values (1, 'general');