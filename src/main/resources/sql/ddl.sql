-- public.t_permission definition

CREATE TABLE IF NOT EXISTS public.t_permission (
	p_parent_id int8 NULL, -- 父权限 ID
	p_name varchar NOT NULL, -- 权限名
	p_description varchar NULL, -- 权限描述
	created_by varchar NULL, -- 创建人
	created_at timestamp now() NOT NULL, -- 创建时间
	updated_by varchar NULL, -- 最后更新人
	updated_at timestamp now() NOT NULL, -- 最后更新时间
	id bigserial NOT NULL, -- 主键 ID
	CONSTRAINT t_permission_pk PRIMARY KEY (id),
	CONSTRAINT t_permission_unique UNIQUE (p_name)
);

COMMENT ON COLUMN public.t_permission.p_parent_id IS '父权限 ID';
COMMENT ON COLUMN public.t_permission.p_name IS '权限名';
COMMENT ON COLUMN public.t_permission.p_description IS '权限描述';
COMMENT ON COLUMN public.t_permission.created_by IS '创建人';
COMMENT ON COLUMN public.t_permission.created_at IS '创建时间';
COMMENT ON COLUMN public.t_permission.updated_by IS '最后更新人';
COMMENT ON COLUMN public.t_permission.updated_at IS '最后更新时间';
COMMENT ON COLUMN public.t_permission.id IS '主键 ID';

-- public.t_role definition

CREATE TABLE IF NOT EXISTS public.t_role (
	r_name varchar NOT NULL, -- 角色名
	r_description varchar NULL, -- 角色描述
	created_by varchar NULL, -- 创建人
	created_at timestamp now() NOT NULL, -- 创建时间
	updated_by varchar NULL, -- 最后更新人
	updated_at timestamp now() NOT NULL, -- 最后更新时间
	id bigserial NOT NULL, -- 主键 ID
	CONSTRAINT t_role_pk PRIMARY KEY (id),
	CONSTRAINT t_role_unique UNIQUE (r_name)
);

COMMENT ON COLUMN public.t_role.r_name IS '角色名';
COMMENT ON COLUMN public.t_role.r_description IS '角色描述';
COMMENT ON COLUMN public.t_role.created_by IS '创建人';
COMMENT ON COLUMN public.t_role.created_at IS '创建时间';
COMMENT ON COLUMN public.t_role.updated_by IS '最后更新人';
COMMENT ON COLUMN public.t_role.updated_at IS '最后更新时间';
COMMENT ON COLUMN public.t_role.id IS '主键 ID';

-- public.t_user definition

CREATE TABLE IF NOT EXISTS public.t_user (
	u_name varchar NOT NULL, -- 用户名
	u_nick_name varchar NULL, -- 昵称
	u_description varchar NULL, -- 用户描述
	created_by varchar NULL, -- 创建人
	created_at timestamp now() NOT NULL, -- 创建时间
	updated_by varchar NULL, -- 最后更新人
	updated_at timestamp now() NOT NULL, -- 最后更新时间
	id bigserial NOT NULL, -- 主键 ID
	CONSTRAINT t_user_pk PRIMARY KEY (id),
	CONSTRAINT t_user_unique UNIQUE (u_name)
);

COMMENT ON COLUMN public.t_user.u_name IS '用户名';
COMMENT ON COLUMN public.t_user.u_nick_name IS '昵称';
COMMENT ON COLUMN public.t_user.u_description IS '用户描述';
COMMENT ON COLUMN public.t_user.created_by IS '创建人';
COMMENT ON COLUMN public.t_user.created_at IS '创建时间';
COMMENT ON COLUMN public.t_user.updated_by IS '最后更新人';
COMMENT ON COLUMN public.t_user.updated_at IS '最后更新时间';
COMMENT ON COLUMN public.t_user.id IS '主键 ID';

-- public.t_user_permission definition

CREATE TABLE IF NOT EXISTS public.t_user_permission (
	up_user_id int8 NOT NULL, -- 用户 ID
	up_permission_id int8 NOT NULL, -- 权限 ID
	created_by varchar NULL, -- 创建人
	created_at timestamp now() NOT NULL, -- 创建时间
	updated_by varchar NULL, -- 最后更新人
	updated_at timestamp now() NOT NULL, -- 最后更新时间
	id bigserial NOT NULL, -- 主键 ID
	CONSTRAINT t_user_permission_pk PRIMARY KEY (id)
);

COMMENT ON COLUMN public.t_user_permission.up_user_id IS '用户 ID';
COMMENT ON COLUMN public.t_user_permission.up_permission_id IS '权限 ID';
COMMENT ON COLUMN public.t_user_permission.created_by IS '创建人';
COMMENT ON COLUMN public.t_user_permission.created_at IS '创建时间';
COMMENT ON COLUMN public.t_user_permission.updated_by IS '最后更新人';
COMMENT ON COLUMN public.t_user_permission.updated_at IS '最后更新时间';
COMMENT ON COLUMN public.t_user_permission.id IS '主键 ID';

-- public.t_user_role definition

CREATE TABLE IF NOT EXISTS public.t_user_role (
	ur_user_id int8 NOT NULL, -- 用户 ID
	ur_role_id int8 NOT NULL, -- 角色 ID
	created_by varchar NULL, -- 创建人
	created_at timestamp now() NOT NULL, -- 创建时间
	updated_by varchar NULL, -- 最后更新人
	updated_at timestamp now() NOT NULL, -- 最后更新时间
	id bigserial NOT NULL, -- 主键 ID
	CONSTRAINT t_user_role_pk PRIMARY KEY (id)
);

COMMENT ON COLUMN public.t_user_role.ur_user_id IS '用户 ID';
COMMENT ON COLUMN public.t_user_role.ur_role_id IS '角色 ID';
COMMENT ON COLUMN public.t_user_role.created_by IS '创建人';
COMMENT ON COLUMN public.t_user_role.created_at IS '创建时间';
COMMENT ON COLUMN public.t_user_role.updated_by IS '最后更新人';
COMMENT ON COLUMN public.t_user_role.updated_at IS '最后更新时间';
COMMENT ON COLUMN public.t_user_role.id IS '主键 ID';

