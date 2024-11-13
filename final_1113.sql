create table broker
(
    bid         varchar(255)                        not null
        primary key,
    office_name varchar(45)                         null,
    broker_name varchar(45)                         not null,
    phone_num   varchar(45)                         not null,
    address     varchar(45)                         not null,
    license_num varchar(45)                         not null,
    password    varchar(255)                        null,
    salt        varchar(45)                         not null,
    email       varchar(45)                         not null,
    last_login  datetime                            null,
    created_at  timestamp default CURRENT_TIMESTAMP not null,
    updated_at  timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP
);

create table dongcodes
(
    dong_code  varchar(10) not null
        primary key,
    sido_name  varchar(30) null,
    gugun_name varchar(30) null,
    dong_name  varchar(30) null
);

create table houseinfos
(
    apt_seq        varchar(20) not null
        primary key,
    sgg_cd         varchar(5)  null,
    umd_cd         varchar(5)  null,
    umd_nm         varchar(20) null,
    jibun          varchar(10) null,
    road_nm_sgg_cd varchar(5)  null,
    road_nm        varchar(20) null,
    road_nm_bonbun varchar(10) null,
    road_nm_bubun  varchar(10) null,
    apt_nm         varchar(40) null,
    build_year     int         null,
    latitude       varchar(45) null,
    longitude      varchar(45) null
);

create table estate
(
    eid         bigint auto_increment
        primary key,
    broker_id   varchar(255)                        not null,
    apt_seq     varchar(20)                         not null,
    type        char(10)                            not null,
    status      char(10)  default '판매중'             not null,
    floor       int                                 not null,
    total_floor int                                 not null,
    area        double                              not null,
    amount      varchar(45)                         not null,
    `desc`      text                                null,
    created_at  timestamp default CURRENT_TIMESTAMP not null,
    updated_at  timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    constraint fk_broker_estate_broker1
        foreign key (broker_id) references broker (bid),
    constraint fk_broker_estate_houseinfos1
        foreign key (apt_seq) references houseinfos (apt_seq)
);

create index fk_broker_estate_broker1_idx
    on estate (broker_id);

create index fk_broker_estate_houseinfos1_idx
    on estate (apt_seq);

create table housedeals
(
    no           int auto_increment
        primary key,
    apt_seq      varchar(20)   null,
    apt_dong     varchar(40)   null,
    floor        varchar(3)    null,
    deal_year    int           null,
    deal_month   int           null,
    deal_day     int           null,
    exclu_use_ar decimal(7, 2) null,
    deal_amount  varchar(10)   null,
    constraint apt_seq_to_house_info
        foreign key (apt_seq) references houseinfos (apt_seq)
);

create index apt_seq_to_house_info_idx
    on housedeals (apt_seq);

create table member
(
    mid        varchar(255)                        not null
        primary key,
    password   varchar(255)                        not null,
    salt       varchar(45)                         not null,
    email      varchar(45)                         not null,
    phone_num  varchar(45)                         null,
    name       varchar(45)                         null,
    created_at timestamp default CURRENT_TIMESTAMP not null,
    updated_at timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    last_login datetime                            null,
    constraint email_UNIQUE
        unique (email)
);

create table favorite
(
    fid        bigint auto_increment
        primary key,
    member_id  varchar(255)                        not null,
    estate_id  bigint                              not null,
    created_at timestamp default CURRENT_TIMESTAMP not null,
    updated_at timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    constraint fk_bookmark_broker_estate1
        foreign key (estate_id) references estate (eid),
    constraint fk_bookmark_member1
        foreign key (member_id) references member (mid)
);

create index fk_bookmark_broker_estate1_idx
    on favorite (estate_id);

create index fk_bookmark_member1_idx
    on favorite (member_id);

create table reservation
(
    rid         bigint auto_increment
        primary key,
    member_id   varchar(255)                        not null,
    broker_id   varchar(255)                        not null,
    start_time  datetime                            null,
    end_time    datetime                            null,
    status      char(10)                            null,
    client_memo varchar(255)                        null,
    broker_memo varchar(255)                        null,
    broker_name varchar(45)                         null,
    created_at  timestamp default CURRENT_TIMESTAMP not null,
    updated_at  timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP,
    constraint fk_reservation_broker1
        foreign key (broker_id) references broker (bid),
    constraint fk_reservation_member
        foreign key (member_id) references member (mid)
            on delete cascade
);

create index fk_reservation_broker1_idx
    on reservation (broker_id);

create index fk_reservation_member_idx
    on reservation (member_id);

create table reservation_estate
(
    id             bigint auto_increment
        primary key,
    reservation_id bigint not null,
    estate_id      bigint not null,
    memo           text   null,
    constraint fk_reservation_estate_broker_estate1
        foreign key (estate_id) references estate (eid),
    constraint fk_reservation_estate_reservation1
        foreign key (reservation_id) references reservation (rid)
);

create index fk_reservation_estate_broker_estate1_idx
    on reservation_estate (estate_id);

create index fk_reservation_estate_reservation1_idx
    on reservation_estate (reservation_id);

create table review
(
    review_id         bigint auto_increment
        primary key,
    reservation_id    bigint                              not null,
    member_id         varchar(255)                        not null,
    broker_id         varchar(255)                        not null,
    review_content    text                                null,
    broker_comment    text                                null,
    review_rating     int                                 not null,
    created_at        timestamp default CURRENT_TIMESTAMP null,
    broker_replied_at datetime                            null,
    constraint fk_review_broker
        foreign key (broker_id) references broker (bid),
    constraint fk_review_member
        foreign key (member_id) references member (mid)
            on delete cascade,
    constraint fk_review_reservation
        foreign key (reservation_id) references reservation (rid),
    check (`review_rating` between 1 and 5)
);


