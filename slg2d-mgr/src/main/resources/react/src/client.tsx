import * as React from 'react';
import * as ReactDOM from 'react-dom';
import { Provider } from 'react-redux';

import './custom';

import ApiClient from './helpers/apiclient';
import myCreateStore from './redux/create';
import getRoutes from './routes';
import { Router } from 'react-router';
import history from './appHistory';

export const client: ApiClient = new ApiClient();

const store = myCreateStore();

// 组件
const component = (
    <Provider store={store} key="provider">
        <Router history={history}>
            <div>
                {getRoutes()}
            </div>
        </Router>
    </Provider>
);

const dest = document.getElementById('content'); // 获取根元素
ReactDOM.render(
    component,
    dest,
);
