set foreign_key_checks = 0;

drop table if exists `category`;
create table `category`
(
    `category_id`        int auto_increment primary key,
    `name`               varchar(64) not null unique,

    -- auditable
    `created_date`       timestamp   default current_timestamp(),
    `last_modified_by`   varchar(32) default 'system',
    `last_modified_date` timestamp   default current_timestamp(),
    `record_status`      int         default 1
) comment 'Blog post category';

drop table if exists `media`;
create table `media`
(
    `media_id`           int auto_increment primary key,
    `uri`                varchar(1024) not null,
    `type`               varchar(32)   null,
    `keywords`           varchar(1024) null,
    `height`             int           null,
    `width`              int           null,
    `size`               int(11)       null,

    -- auditable
    `created_date`       timestamp   default current_timestamp(),
    `last_modified_by`   varchar(32) default 'system',
    `last_modified_date` timestamp   default current_timestamp(),
    `record_status`      int         default 1
) comment 'Uploaded image shown on the blog post';

drop table if exists `role`;
create table `role`
(
    `role_id`            int auto_increment primary key,
    `name`               varchar(32) not null unique,

    -- auditable
    `created_date`       timestamp   default current_timestamp(),
    `last_modified_by`   varchar(32) default 'system',
    `last_modified_date` timestamp   default current_timestamp(),
    `record_status`      int         default 1
) comment 'User permissions';

drop table if exists `tag`;
create table `tag`
(
    `tag_id`             int auto_increment primary key,
    `name`               varchar(32) not null unique,

    -- auditable
    `created_date`       timestamp   default current_timestamp(),
    `last_modified_by`   varchar(32) default 'system',
    `last_modified_date` timestamp   default current_timestamp(),
    `record_status`      int         default 1
) comment 'Blog post tag';

drop table if exists `user`;
create table `user`
(
    `user_id`            int auto_increment primary key,
    `username`           varchar(32)  not null,
    `image_fk`           int          null,
    `password`           varchar(512) not null,
    `email`              varchar(32)  not null,
    `first_name`         varchar(32)  not null,
    `last_name`          varchar(32)  not null,
    `about`              text         null,
    `display_name`       varchar(32)  not null,

    -- auditable
    `created_date`       timestamp   default current_timestamp(),
    `last_modified_by`   varchar(32) default 'system',
    `last_modified_date` timestamp   default current_timestamp(),
    `record_status`      int         default 1,

    constraint foreign key `fk_user_media` (`image_fk`) references `media` (`media_id`)
        on update cascade on delete set null
) comment 'Blog user or author';

drop table if exists `contact`;
create table `contact`
(
    `contact_id`         int auto_increment primary key,
    `contact_type`       varchar(32)  not null,
    `user_fk`            int          not null,
    `value`              varchar(128) not null,

    -- auditable
    `created_date`       timestamp   default current_timestamp(),
    `last_modified_by`   varchar(32) default 'system',
    `last_modified_date` timestamp   default current_timestamp(),
    `record_status`      int         default 1,

    constraint `fk_user_contact` foreign key (`user_fk`) references `user` (`user_id`)
) comment 'User contact information entry';

drop table if exists `post`;
create table `post`
(
    `post_id`            int auto_increment primary key,
    `user_fk`            int                   null,
    `category_fk`        int                   not null,
    `title`              varchar(255)          not null,
    `excerpt`            text                  not null,
    `body`               text                  not null,
    `published`          tinyint(1)  default 0 null,
    `views`              bigint                null,
    `slug`               varchar(64)           not null,
    `date_posted`        date        default current_date(),


    -- auditable
    `created_date`       timestamp   default current_timestamp(),
    `last_modified_by`   varchar(32) default 'system',
    `last_modified_date` timestamp   default current_timestamp(),
    `record_status`      int         default 1,

    constraint `fk_post_author` foreign key (`user_fk`) references `user` (`user_id`)
        on update cascade on delete set null,
    constraint `fk_post_category` foreign key (`category_fk`) references `category` (`category_id`)
        on update cascade on delete restrict
) comment 'Blog post';

drop table if exists `comment`;
create table `comment`
(
    `comment_id`         int auto_increment primary key,
    `user_fk`            int  not null,
    `post_fk`            int  null,
    `comment_fk`         int  null,
    `body`               text not null,

    -- auditable
    `created_date`       timestamp   default current_timestamp(),
    `last_modified_by`   varchar(32) default 'system',
    `last_modified_date` timestamp   default current_timestamp(),
    `record_status`      int         default 1,

    constraint `fk_post_comment` foreign key (`post_fk`) references `post` (`post_id`)
        on update cascade on delete cascade,
    constraint `fk_comment_self` foreign key (`comment_fk`) references `comment` (`comment_id`)
        on update cascade on delete cascade,
    constraint `fk_user_comment` foreign key (`user_fk`) references `user` (`user_id`)
        on update cascade on delete cascade
) comment 'User comment on a post';

drop table if exists `post_tag`;
create table `post_tag`
(
    `tag_fk`  int not null,
    `post_fk` int not null,
    primary key (`tag_fk`, `post_fk`),
    constraint `fk_post_tag_tag` foreign key (`tag_fk`) references `tag` (`tag_id`)
        on update cascade on delete cascade,
    constraint `fk_post_tag_post` foreign key (`post_fk`) references `post` (`post_id`)
        on update cascade on delete cascade
);

drop table if exists `user_role`;
create table `user_role`
(
    `role_fk` int not null,
    `user_fk` int not null,
    primary key (`role_fk`, `user_fk`),
    constraint fk_user_role_role foreign key (`role_fk`) references `role` (`role_id`)
        on update cascade on delete cascade,
    constraint fk_user_role_user foreign key (`user_fk`) references `user` (`user_id`)
        on update cascade on delete cascade
);

drop table if exists `notification`;
create table `notification`
(
    `notification_id` int auto_increment primary key,
    `title` varchar(128) not null,
    `body` text not null,
    `seen` tinyint default 0,
    `read` tinyint default 0,
    `user_fk` int null,
    `type` varchar(32) null,
    `action_url` varchar(256) null,

    -- auditable
    `created_date`       timestamp   default current_timestamp(),
    `last_modified_by`   varchar(32) default 'system',
    `last_modified_date` timestamp   default current_timestamp(),
    `record_status`      int         default 1,

    constraint `fk_notification_user` foreign key (`user_fk`) references `user`(`user_id`)
        on update cascade on delete set null
);

set foreign_key_checks = 1;