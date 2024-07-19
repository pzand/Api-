-- 接口信息
create table if not exists yuapi.`interface_info`
(
    `id` bigint not null auto_increment comment '主键' primary key,
    `name` varchar(256) not null comment '用户名',
    `description` varchar(256) null comment '用户名',
    `url` varchar(512) not null comment '接口地址',
    `requestHeader` text null comment '请求头',
    `responseHeader` text null comment '响应头',
    `status` int default 0 not null comment '接口状态 (0-关闭, 1-开启)',
    `method` varchar(256) not null comment '请求类型',
    `userId` bigint not null comment '创建人',
    `createTime` datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `updateTime` datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `isDeleted` tinyint default 0 not null comment '是否删除(0-未删, 1-已删)'
) comment '接口信息';

insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('白明哲', '钱楷瑞', 'www.donovan-bergnaum.info', '廖天翊', '郝煜祺', 0, '张天磊', 59818);
insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('丁智渊', '熊智渊', 'www.rolland-dubuque.net', '贺煜祺', '邹煜祺', 0, '高天磊', 230987);
insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('胡钰轩', '曾风华', 'www.katherin-dickens.org', '田熠彤', '毛荣轩', 0, '王天磊', 506300675);
insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('周熠彤', '毛皓轩', 'www.setsuko-kassulke.biz', '梁文轩', '魏擎苍', 0, '胡志泽', 655853);
insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('于浩轩', '方修洁', 'www.dorothea-fisher.org', '傅胤祥', '何鹏煊', 0, '余鹏煊', 814730844);
insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('叶子骞', '洪思聪', 'www.delmer-marvin.io', '姚哲瀚', '孙驰', 0, '孟弘文', 1251996377);
insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('赖昊天', '黄苑博', 'www.laurel-tillman.name', '韩鹏', '郭嘉熙', 0, '刘志泽', 0);
insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('吕聪健', '朱驰', 'www.violet-abbott.com', '尹健雄', '苏熠彤', 0, '林楷瑞', 399351);
insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('龙聪健', '高峻熙', 'www.columbus-lebsack.io', '邹天磊', '邱炎彬', 0, '吴泽洋', 26531);
insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('陈子涵', '彭思远', 'www.chas-reinger.net', '彭瑞霖', '廖瑞霖', 0, '雷苑博', 48132033);
insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('史绍辉', '吴彬', 'www.ariana-nitzsche.io', '王智辉', '董钰轩', 0, '余思远', 8020564);
insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('夏果', '罗文轩', 'www.oralee-dare.net', '邱晓啸', '谢昊然', 0, '龙弘文', 38308);
insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('吕文博', '丁烨霖', 'www.ross-bradtke.biz', '熊健雄', '叶炫明', 0, '陶文昊', 908);
insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('邵文轩', '石弘文', 'www.franklyn-gusikowski.io', '曾立轩', '廖煜祺', 0, '黎鸿煊', 19590);
insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('陶鹏飞', '洪明', 'www.bob-abernathy.co', '薛智渊', '武鹭洋', 0, '史雪松', 93760);
insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('金君浩', '孟展鹏', 'www.donald-keebler.com', '杨子骞', '程弘文', 0, '徐伟祺', 7);
insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('丁鑫磊', '朱涛', 'www.lily-mitchell.info', '孟越泽', '尹修洁', 0, '傅天宇', 685558482);
insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('陈耀杰', '田弘文', 'www.avery-raynor.org', '万思淼', '胡鹏飞', 0, '周思远', 56660740);
insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('谢绍辉', '余鸿煊', 'www.rosendo-rutherford.name', '陶泽洋', '董子默', 0, '万伟泽', 939162);
insert into yuapi.`interface_info` (`name`, `description`, `url`, `requestHeader`, `responseHeader`, `status`, `method`, `userId`) values ('李志泽', '廖苑博', 'www.alex-christiansen.biz', '蔡擎苍', '戴耀杰', 0, '黎修杰', 9);