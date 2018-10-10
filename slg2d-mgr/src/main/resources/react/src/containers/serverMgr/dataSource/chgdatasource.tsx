import * as React from 'react';
import { ChangeEvent } from 'react';
import { connect } from 'react-redux';
import { match } from 'react-router';
import { bindActionCreators, Dispatch } from 'redux';
import { Button, Col, Form, Input, Row, Select } from 'antd';
import { History } from 'history';

import { ICombineState } from '../../../redux/reducer';
import { DATA_SOURCE } from '../../../const/funcType';
import { IReqAction } from '../../../sagas/ibaseaction';
import { IDataSource, IMarkCreateModifyDataSource } from '../../../redux/modules/serverMgr/dataSource';
import { chgDataSource, markCreateOrModifyDataSource } from '../../../sagas/serverMgr/dataSourceMgr';
import { SelectValue } from 'antd/lib/select';
import { ICommCfg } from '../../../redux/modules/serverMgr/commCfg';

const FormItem = Form.Item;
const Option = Select.Option;

interface IChgDataSourceParam {
    configName: string;
}

interface IStateProps {
    history: History;
    match: match<IChgDataSourceParam>;
    dataSources: IDataSource[];
    commCfgs: ICommCfg[];
    atCreatingOrModifying: boolean;
}

interface IDispatchProps {
    markCreateOrModifyDataSource(atCreatingOrModifying: boolean): IMarkCreateModifyDataSource;
    chgDataSource(data: {}): IReqAction<{}>;
}

type IAppProps = IStateProps & IDispatchProps;

function mapStateToProps(state: ICombineState) {
    return {
        dataSources: state.serverMgr.dataSource.dataSources,
        commCfgs: state.serverMgr.commCfg.commCfgs,
        atCreatingOrModifying: state.serverMgr.dataSource.atCreatingOrModifying,
    };
}

const mapDispatchToProps = (dispatch: Dispatch<{}>) => {
    return bindActionCreators({
        markCreateOrModifyDataSource,
        chgDataSource,
    }, dispatch);
};

class Chgdatasource extends React.Component<IAppProps, IDataSource> {

    constructor(props: IAppProps) {
        super(props);

        const ip = this.props.match.params.configName;
        let showKey = '';
        let configName = '';
        let shardId = 0;
        let socket = '';
        let database = '';
        let user = '';
        let password = '';
        let clusterId = 0;
        this.props.dataSources.map((datasource: IDataSource) => {
            if (datasource.configName === ip) {
                showKey = datasource.showKey;
                configName = datasource.configName;
                shardId = datasource.shardId;
                socket = datasource.socket;
                database = datasource.database;
                user = datasource.user;
                password = datasource.password;
                clusterId = datasource.clusterId;
            }
        });

        this.state = {
            showKey,
            configName,
            shardId,
            socket,
            database,
            user,
            password,
            clusterId,
        };

        this.props.markCreateOrModifyDataSource(true);
    }

    componentWillReceiveProps(nextProps: IAppProps) {
        if (!nextProps.atCreatingOrModifying && this.props.atCreatingOrModifying) {
            this.props.history.push(DATA_SOURCE);
        }
    }

    cancelAdd = () => {
        this.props.markCreateOrModifyDataSource(false);
    };

    handleSubmit = () => {
        const data = {
            showKey: this.state.showKey,
            configName: '' + this.state.clusterId,
            shardId: this.state.shardId,
            socket: this.state.socket,
            database: this.state.database,
            user: this.state.user,
            password: this.state.password,
            clusterId: this.state.clusterId,
        };
        this.props.chgDataSource(data);
    };

    updateDataSourceshardId = (event: ChangeEvent<HTMLInputElement>) => {
        const shardId = parseInt(event.target.value, 10);
        this.setState({
            shardId: shardId,
        });
    };

    updateDataSourceSocket = (event: ChangeEvent<HTMLInputElement>) => {
        const socket = event.target.value;
        this.setState({
            socket: socket,
        });
    };

    updateDataSourceDB = (event: ChangeEvent<HTMLInputElement>) => {
        const database = event.target.value;
        this.setState({
            database: database,
        });
    };

    updateDataSourceUser = (event: ChangeEvent<HTMLInputElement>) => {
        const user = event.target.value;
        this.setState({
            user: user,
        });
    };

    updateDataSourcePass = (event: ChangeEvent<HTMLInputElement>) => {
        const password = event.target.value;
        this.setState({
            password: password,
        });
    };

    updateClusterId = (value: SelectValue) => {
        const clusterId = parseInt(value as string, 10);
        this.setState({
            clusterId,
        });
    };

    render() {
        const formItemLayout = {
            labelCol: {span: 4},
            wrapperCol: {span: 10},
        };
        const tailFormItemLayout = {
            wrapperCol: {
                offset: 4,
                span: 10,
            },
        };

        const commCfgs = this.props.commCfgs;
        const commCfgList = commCfgs.map((commCfg: ICommCfg) => {
            return (<Option value={'' + commCfg.id} key={commCfg.id}>{commCfg.groupName}</Option>);
        });

        return (
            <Row>
                <Col span={24}>
                    <Form>
                        <FormItem {...formItemLayout} label="集群">
                            <Select value={'' + this.state.clusterId} onChange={this.updateClusterId}>
                                {commCfgList}
                            </Select>
                        </FormItem>
                        <FormItem {...formItemLayout} label="shardId">
                            <Input type="number" value={this.state.shardId}
                                   onChange={this.updateDataSourceshardId}/>
                        </FormItem>
                        <FormItem {...formItemLayout} label="地址">
                            <Input type="text" value={this.state.socket}
                                   onChange={this.updateDataSourceSocket}/>
                        </FormItem>
                        <FormItem {...formItemLayout} label="数据库名称">
                            <Input type="text" value={this.state.database}
                                   onChange={this.updateDataSourceDB}/>
                        </FormItem>
                        <FormItem {...formItemLayout} label="用户名">
                            <Input type="text" value={this.state.user}
                                   onChange={this.updateDataSourceUser}/>
                        </FormItem>
                        <FormItem {...formItemLayout} label="密码">
                            <Input type="text" value={this.state.password}
                                   onChange={this.updateDataSourcePass}/>
                        </FormItem>
                        <FormItem {...tailFormItemLayout}>
                            <Button type="primary" onClick={this.handleSubmit}>修改</Button>
                            <Button type="primary" onClick={this.cancelAdd}>取消</Button>
                        </FormItem>
                    </Form>
                </Col>
            </Row>
        );
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Chgdatasource);