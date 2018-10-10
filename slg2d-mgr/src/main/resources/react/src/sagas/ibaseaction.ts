export function actionSucceeded(action: string): string {
    return action + '_SUCCEEDED';
}

export function actionFailed(action: string): string {
    return action + '_FAILED';
}

export class FailedAction {
    type: string;

    constructor(type: string,
                public rt: number,
                public errorMsg: string) {
        this.type = actionFailed(type);
    }
}

// 所有的行为种类
export const SHOW_SPEC_FUNC = 'SHOW_SPEC_FUNC';

export const SELECT_AREA = 'SELECT_AREA';

// GM账号组相关
export const REQ_QUERY_ACCGROUP_MGR = 'REQ_QUERY_ACCGROUP_MGR';
export const REQ_QUERY_ACCRIGHTS = 'REQ_QUERY_ACCRIGHTS';
export const REQ_ADD_ACCGROUP = 'REQ_ADD_ACCGROUP';
export const REQ_CHG_ACCGROUP = 'REQ_CHG_ACCGROUP';
export const REQ_DEL_ACCGROUP = 'REQ_DEL_ACCGROUP';

// GM账号相关
export const REQ_QUERY_ACC_MGR = 'REQ_QUERY_ACC_MGR';
export const REQ_ADD_ACC = 'REQ_ADD_ACC';
export const REQ_CHG_ACC = 'REQ_CHG_ACC';
export const REQ_DEL_ACC = 'REQ_DEL_ACC';
export const REQ_MOD_ACC_PWD = 'REQ_MOD_ACC_PWD';

// 登录
export const REQ_LOGIN = 'REQ_LOGIN';

// 集群配置
export const MARK_CREATE_MODIFY_COMM_CFG = 'MARK_CREATE_MODIFY_COMM_CFG';

export const REQ_QUERY_COMM_CFG = 'REQ_QUERY_COMM_CFG';
export const REQ_ADD_COMM_CFG = 'REQ_ADD_COMM_CFG';
export const REQ_CHG_COMM_CFG = 'REQ_CHG_COMM_CFG';
export const REQ_DEL_COMM_CFG = 'REQ_DEL_COMM_CFG';
export const REQ_QUERY_COMM_DETAIL = 'REQ_QUERY_COMM_DETAIL';

// 游戏服配置
export const MARK_AREA_CACHE_DIRTY = 'MARK_AREA_CACHE_DIRTY';
export const MARK_CREATE_MODIFY_AREA_WORLD = 'MARK_CREATE_MODIFY_AREA_WORLD';

export const REQ_QUERY_WORLD_AREA = 'REQ_QUERY_WORLD_AREA';
export const REQ_ADD_WORLD_AREA = 'REQ_ADD_WORLD_AREA';
export const REQ_CHG_WORLD_AREA = 'REQ_CHG_WORLD_AREA';
export const REQ_DEL_WORLD_AREA = 'REQ_DEL_WORLD_AREA';

// 平台配置
export const MARK_CREATE_MODIFY_PLATFORM = 'MARK_CREATE_MODIFY_PLATFORM';

export const REQ_QUERY_PLT = 'REQ_QUERY_PLT';
export const REQ_ADD_PLT = 'REQ_ADD_PLT';
export const REQ_DEL_PLT = 'REQ_DEL_PLT';
export const REQ_CHG_PLT = 'REQ_CHG_PLT';

// 进程配置
export const SAVE_PROCESS_FILTERS = 'SAVE_PROCESS_FILTERS';
export const MARK_CREATE_MODIFY_PROCESS = 'MARK_CREATE_MODIFY_PROCESS';

export const REQ_QUERY_PROCESS = 'REQ_QUERY_PROCESS';
export const REQ_ADD_PROCESS = 'REQ_ADD_PROCESS';
export const REQ_CHG_PROCESS = 'REQ_CHG_PROCESS';
export const REQ_DEL_PROCESS = 'REQ_DEL_PROCESS';

// 数据库配置
export const MARK_CREATE_MODIFY_DATA_SOURCE = 'MARK_CREATE_MODIFY_DATA_SOURCE';

export const REQ_QUERY_DATA_SOURCE = 'REQ_QUERY_DATA_SOURCE';
export const REQ_ADD_DATA_SOURCE = 'REQ_ADD_DATA_SOURCE';
export const REQ_CHG_DATA_SOURCE = 'REQ_CHG_DATA_SOURCE';
export const REQ_DEL_DATA_SOURCE = 'REQ_DEL_DATA_SOURCE';

// 基本的Action类型
export interface IBaseAction {
    type: string;
}

// 请求类的行为类型
export interface IReqAction<T> {
    type: string;
    payload: T;
}