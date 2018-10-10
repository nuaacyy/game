import { IBaseAction, SELECT_AREA } from '../../sagas/ibaseaction';

// 切换功能
export function selectArea(areaId: number): {} {
    return {
        type: SELECT_AREA,
        areaId,
    };
}

export class SelectAreaState {
    areaId: number = 1;
}

const reducerMap = new Map<string, (state: SelectAreaState, action: IBaseAction) => SelectAreaState>();

export interface ISelectAreaAction {
    type: string;
    areaId: number;
}

reducerMap[SELECT_AREA] = (state: SelectAreaState, action: ISelectAreaAction) => ({
    ...state,
    areaId: action.areaId,
});

export default function (state: SelectAreaState = new SelectAreaState(), action: IBaseAction): SelectAreaState {
    let reducer = reducerMap[action.type];
    if (reducer !== undefined && reducer !== null) {
        return reducer(state, action);
    } else {
        return state;
    }
}
