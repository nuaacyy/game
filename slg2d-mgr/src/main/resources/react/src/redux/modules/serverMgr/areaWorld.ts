import moment from 'moment';
import {
    actionSucceeded,
    IBaseAction,
    MARK_CREATE_MODIFY_AREA_WORLD,
    REQ_ADD_WORLD_AREA,
    REQ_CHG_WORLD_AREA,
    REQ_QUERY_WORLD_AREA
} from '../../../sagas/ibaseaction';

export interface IWorldArea {
    id: number;
    areaNo: number;
    payKey: string;
    opgameId: number;
    loginKey: string;
    areaState: number;
    pltAreaNo: number;
    gameAreaName: string;
    databaseName: string;
    giftKey: string;
    logTime: moment.Moment;
    openAreaDate: number;
    operationState: number;
    whichAction: number;
    clusterId: number;
}

export class AreaWorldState {
    areas: IWorldArea[] = [];
    atCreatingOrModifying: boolean; // 是否正在创建或修改
    dirty: boolean;
}

const reducerMap = new Map<string, (state: AreaWorldState, action: IBaseAction) => AreaWorldState>();

//
export interface IQueryAreaSucceedAction {
    type: string;
    payload: IWorldArea[];
}

reducerMap[actionSucceeded(REQ_QUERY_WORLD_AREA)] = (state: AreaWorldState, action: IQueryAreaSucceedAction) => ({
    ...state,
    areas: action.payload,
});

//
reducerMap[actionSucceeded(REQ_ADD_WORLD_AREA)] = (state: AreaWorldState, action: IBaseAction) => ({
    ...state,
    atCreatingOrModifying: false, // 结束创建
});

//
reducerMap[actionSucceeded(REQ_CHG_WORLD_AREA)] = (state: AreaWorldState, action: IBaseAction) => ({
    ...state,
    atCreatingOrModifying: false, // 结束修改
});

//
export interface IMarkCreateModifyAreaWorld {
    type: string;
    atCreatingOrModifying: boolean;
}

reducerMap[MARK_CREATE_MODIFY_AREA_WORLD] = (state: AreaWorldState, action: IMarkCreateModifyAreaWorld) => ({
    ...state,
    atCreatingOrModifying: action.atCreatingOrModifying,
});

export default function (state: AreaWorldState = new AreaWorldState(), action: IBaseAction): AreaWorldState {
    let reducer = reducerMap[action.type];
    if (reducer !== undefined && reducer !== null) {
        return reducer(state, action);
    } else {
        return state;
    }
}