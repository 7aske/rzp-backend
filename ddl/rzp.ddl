create table category
(
	id_category int auto_increment
		primary key,
	category_name varchar(64) not null
);

create table contact_type
(
	id_contact_type int auto_increment
		primary key,
	contact_type_name varchar(128) not null,
	contact_type_value_type varchar(32) not null
);

create table media
(
	id_media int auto_increment
		primary key,
	media_filepath varchar(1024) not null
);

create table role
(
	id_role int auto_increment
		primary key,
	role_name varchar(32) not null
);

create table tag
(
	id_tag int auto_increment
		primary key,
	tag_name varchar(64) not null
);

create table user
(
	id_user int auto_increment
		primary key,
	user_username varchar(128) not null,
	user_email varchar(128) not null,
	user_password varchar(512) not null,
	user_first_name varchar(128) not null,
	user_last_name varchar(128) not null,
	user_address varchar(128) null,
	user_about text null,
	user_display_name varchar(128) not null,
	user_date_created datetime null
);

create table contact
(
	id_contact int auto_increment
		primary key,
	id_contact_type int null,
	id_user int null,
	contact_value varchar(128) not null,
	constraint fk_contact_contact_type
		foreign key (id_contact_type) references contact_type (id_contact_type),
	constraint fk_user_contact
		foreign key (id_user) references user (id_user)
);

create table post
(
	id_post int auto_increment
		primary key,
	id_user int null,
	id_category int not null,
	post_title varchar(255) not null,
	post_excerpt text not null,
	post_body text not null,
	post_date_posted timestamp default current_timestamp() not null on update current_timestamp(),
	post_deleted tinyint(1) default 0 null,
	post_published tinyint(1) default 0 null,
	post_views bigint null,
	constraint fk_post_author2
		foreign key (id_user) references user (id_user),
	constraint fk_post_category2
		foreign key (id_category) references category (id_category)
);

create table comment
(
	id_comment int auto_increment
		primary key,
	id_user int not null,
	id_post int null,
	comment_body text not null,
	comment_date_posted timestamp default current_timestamp() not null on update current_timestamp(),
	constraint fk_post_comment2
		foreign key (id_post) references post (id_post),
	constraint fk_user_comment2
		foreign key (id_user) references user (id_user)
);

create table comment_upvote
(
	comment_upvote_hash varchar(128) not null
		primary key,
	id_comment int not null,
	constraint fk_comment_upvote2
		foreign key (id_comment) references comment (id_comment)
);

create table post_media
(
	id_media int not null,
	id_post int not null,
	primary key (id_media, id_post),
	constraint fk_post_media
		foreign key (id_media) references media (id_media),
	constraint fk_post_media2
		foreign key (id_post) references post (id_post)
);

create table post_tag
(
	id_tag int not null,
	id_post int not null,
	primary key (id_tag, id_post),
	constraint fk_post_tag2
		foreign key (id_tag) references tag (id_tag),
	constraint fk_post_tag3
		foreign key (id_post) references post (id_post)
);

create table post_upvote
(
	post_upvote_hash varchar(128) not null
		primary key,
	id_post int not null,
	constraint fk_post_upvote2
		foreign key (id_post) references post (id_post)
);

create table user_role
(
	id_role int not null,
	id_user int not null,
	primary key (id_role, id_user),
	constraint fk_user_role
		foreign key (id_role) references role (id_role),
	constraint fk_user_role2
		foreign key (id_user) references user (id_user)
);

