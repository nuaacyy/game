import { applyMiddleware, createStore, Store } from 'redux';
import createSagaMiddleware from 'redux-saga';
import reducer from './reducer'; // 得到reducer
import { rootSaga } from '../sagas/sagas';

// create the saga middleware
export const sagaMiddleware = createSagaMiddleware();

const initialState = {};

// 创建store的方法
// data是最原始的state
export default function myCreateStore(): Store<{}> {

    const store: Store<{}> = createStore(reducer,
        initialState,
        applyMiddleware(sagaMiddleware),
    );

    sagaMiddleware.run(rootSaga);

    return store;
}