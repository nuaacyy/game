import * as React from 'react';
import { Route, RouteComponentProps, Switch } from 'react-router';

import Login from './containers/login';
import Panels from './containers/panels';

export default () => {
    // 返回路由组件
    const appRoute = ({match}: RouteComponentProps<{}>) => {
        console.info('match.path ', match.path);
        return (
            <div className="container-fluid">
                <Switch>
                    <Route path={`${match.path}panels`} component={Panels}/>
                    <Route component={Login}/>
                </Switch>
            </div>
        );
    };

    // exact={true} path={`/static/`}

    return (
        <Route path={__PUBLIC_PATH__} component={appRoute} />
    );
};