import { actionSucceeded, IBaseAction, REQ_QUERY_ACCGROUP_MGR, REQ_QUERY_ACCRIGHTS, } from '../../sagas/ibaseaction';

export interface IMgrAccGroup {
    id: number;
    isQa: number;
    groupName: string;
    groupState: number;
    rights: IMgrAccGroupRight[];
    platforms: number[];
}

export interface IMgrAccGroupRight {
    id: number;
    info: string;
}

export class AccGroupState {
    mgraccgroups: IMgrAccGroup[] = [];
    accRights: IMgrAccGroupRight[] = [];
}

const reducerMap = new Map<string, (state: AccGroupState, action: IBaseAction) => AccGroupState>();

//

export interface IQueryAccGroupMgrSucceedAction {
    type: string;
    payload: IMgrAccGroup[];
}

reducerMap[actionSucceeded(REQ_QUERY_ACCGROUP_MGR)] = (state: AccGroupState,
                                                       action: IQueryAccGroupMgrSucceedAction) => ({
    ...state,
    mgraccgroups: action.payload,
});

//

export interface IQueryAccRightsSucceedAction {
    type: string;
    payload: IMgrAccGroupRight[];
}

reducerMap[actionSucceeded(REQ_QUERY_ACCRIGHTS)] = (state: AccGroupState, action: IQueryAccRightsSucceedAction) => ({
    ...state,
    accRights: action.payload,
});

export default function (state: AccGroupState = new AccGroupState(), action: IBaseAction): AccGroupState {
    let reducer = reducerMap[action.type];
    if (reducer !== undefined && reducer !== null) {
        return reducer(state, action);
    } else {
        return state;
    }
}