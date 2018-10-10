import {
    actionSucceeded,
    IBaseAction,
    MARK_CREATE_MODIFY_DATA_SOURCE,
    REQ_ADD_DATA_SOURCE,
    REQ_CHG_DATA_SOURCE,
    REQ_QUERY_DATA_SOURCE
} from '../../../sagas/ibaseaction';

export interface IDataSource {
    showKey: string;
    configName: string;
    shardId: number;
    socket: string;
    database: string;
    user: string;
    password: string;
    clusterId: number;
}

export class DataSourceState {
    dataSources: IDataSource[] = [];
    atCreatingOrModifying: boolean;
}

const reducerMap = new Map<string, (state: DataSourceState, action: IBaseAction) => DataSourceState>();

//
export interface IQueryDataSourceSucceedAction {
    type: string;
    payload: IDataSource[];
}

reducerMap[actionSucceeded(REQ_QUERY_DATA_SOURCE)] = (state: DataSourceState,
                                                      action: IQueryDataSourceSucceedAction) => ({
    ...state,
    dataSources: action.payload,
});

//
reducerMap[actionSucceeded(REQ_ADD_DATA_SOURCE)] = (state: DataSourceState, action: IBaseAction) => ({
    ...state,
    atCreatingOrModifying: false, // 结束创建
});

//
reducerMap[actionSucceeded(REQ_CHG_DATA_SOURCE)] = (state: DataSourceState, action: IBaseAction) => ({
    ...state,
    atCreatingOrModifying: false, // 结束修改
});

//
export interface IMarkCreateModifyDataSource {
    type: string;
    atCreatingOrModifying: boolean;
}

reducerMap[MARK_CREATE_MODIFY_DATA_SOURCE] = (state: DataSourceState, action: IMarkCreateModifyDataSource) => ({
    ...state,
    atCreatingOrModifying: action.atCreatingOrModifying,
});

export default function (state: DataSourceState = new DataSourceState(), action: IBaseAction): DataSourceState {
    let reducer = reducerMap[action.type];
    if (reducer !== undefined && reducer !== null) {
        return reducer(state, action);
    } else {
        return state;
    }
}
