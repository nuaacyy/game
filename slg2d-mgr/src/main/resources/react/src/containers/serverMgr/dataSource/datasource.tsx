import * as React from 'react';
import { connect } from 'react-redux';

import { bindActionCreators, Dispatch } from 'redux';
import { Button, Col, Row, Table } from 'antd';
import { History } from 'history';

import { ICombineState } from '../../../redux/reducer';
import { ADD_DATA_SOURCE, CHG_DATA_SOURCE } from '../../../const/funcType';
import { IDataSource } from '../../../redux/modules/serverMgr/dataSource';
import { deleteDataSource, IDelDataSource } from '../../../sagas/serverMgr/dataSourceMgr';
import { IReqAction } from '../../../sagas/ibaseaction';
import confirm from 'antd/es/modal/confirm';
import { ICommCfg } from '../../../redux/modules/serverMgr/commCfg';

const ButtonGroup = Button.Group;

interface IStateProps {
    history: History;
    dataSources: IDataSource[];
    commCfgs: ICommCfg[];
}

interface IDispatchProps {
    deleteDataSource(clusterId: number, shardId: number): IReqAction<IDelDataSource>;
}

type IAppProps = IStateProps & IDispatchProps;

function mapStateToProps(state: ICombineState) {
    return {
        dataSources: state.serverMgr.dataSource.dataSources,
        commCfgs: state.serverMgr.commCfg.commCfgs,
    };
}

const mapDispatchToProps = (dispatch: Dispatch<{}>) => {
    return bindActionCreators({
        deleteDataSource
    }, dispatch);
};

interface IState {

}

class DataSourceMgr extends React.Component<IAppProps, IState> {

    constructor(props: IAppProps) {
        super(props);

        this.state = {};
    }

    delConfirm = (clusterId: number, shardId: number) => {
      confirm({
          title: '删除该条配置',
          content: '你确定要删除这条记录?',
          onOk: () => {
              this.props.deleteDataSource(clusterId, shardId);
          }
      });
    };

    chgDataSource = (path: string) => {
        this.props.history.push(path);
    };

    switch2Add = () => {
        this.props.history.push(ADD_DATA_SOURCE);
    };

    render() {

        const dataSources = this.props.dataSources;

        const commCfgNameMap = new Map<number, string>();
        const commCfgs = this.props.commCfgs;
        commCfgs.map((commCfg: ICommCfg) => commCfgNameMap[commCfg.id] = commCfg.groupName);

        const columns = [
            {title: 'IP', dataIndex: 'configName', key: 'configName'},
            {
                title: '所属集群', key: 'clusterId',
                render: (text: string, record: IDataSource) => {
                    return commCfgNameMap[record.clusterId];
                },
            },
            {title: 'shardId', dataIndex: 'shardId', key: 'shardId'},
            {title: '地址', dataIndex: 'socket', key: 'socket'},
            {title: '数据库名称', dataIndex: 'database', key: 'database'},
            {title: '用户名', dataIndex: 'user', key: 'user'},
            {title: '密码', dataIndex: 'password', key: 'password'},
            {
                title: '操作', key: 'action',
                render: (text: string, record: IDataSource) => (
                    <div>
                        <ButtonGroup>
                            <Button type="primary" onClick={() => {
                                this.chgDataSource(CHG_DATA_SOURCE + '/' + record.configName);
                            }}>修改</Button>
                            <Button type="danger" onClick={() => {
                                this.delConfirm(record.clusterId, record.shardId);
                            }}>删除</Button>
                        </ButtonGroup>
                    </div>
                ),
            },
        ];

        return (
            <div>
                <Row>
                    <Col span={24} className="col-sm-offset-5">
                        <Button type="primary" onClick={this.switch2Add}>添加数据库配置</Button>
                    </Col>
                </Row>

                <Row>
                    <Col span={24}>
                        <Table rowKey="showKey" pagination={false} columns={columns} dataSource={dataSources}
                               size="small"/>
                    </Col>
                </Row>

            </div>
        );
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(DataSourceMgr);