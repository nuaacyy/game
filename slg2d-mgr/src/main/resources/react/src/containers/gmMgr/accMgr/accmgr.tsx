import * as React from 'react';
import { connect } from 'react-redux';

import { bindActionCreators, Dispatch } from 'redux';
import { Button, Col, Modal, Row, Table } from 'antd';
import { deleteAccount, IDelAccountData } from '../../../sagas/accMgr';
import { History } from 'history';
import { ICombineState } from '../../../redux/reducer';
import { ADD_ACCOUNT, CHG_ACCOUNT } from '../../../const/funcType';
import { IMgrAccount } from '../../../redux/modules/acc';
import { IReqAction } from '../../../sagas/ibaseaction';

const confirm = Modal.confirm;

interface IStateProps {
    history: History;
    mgraccounts: IMgrAccount[];
}

interface IDispatchProps {
    deleteAccount(id: number): IReqAction<IDelAccountData>;
}

type IAppProps = IStateProps & IDispatchProps;

const mapStateToProps = (state: ICombineState) => {
    return {
        mgraccounts: state.acc.mgraccounts,
    };
};

const mapDispatchToProps = (dispatch: Dispatch<{}>) => {
    return bindActionCreators({
        deleteAccount,
    }, dispatch);
};

interface IState {

}

class AccMgr extends React.Component<IAppProps, IState> {

    constructor(props: IAppProps) {
        super(props);

        this.state = {};
    }

    delConfirm = (recordId: number) => {
        confirm({
            title: '删除账号配置',
            content: '你确定要删除这条记录？',
            onOk: () => {
                this.props.deleteAccount(recordId);
            },
        });
    };

    chgAccount = (path: string) => {
        this.props.history.push(path);
    };

    switch2Add = () => {
        this.props.history.push(ADD_ACCOUNT);
    };

    render() {
        const mgraccounts = this.props.mgraccounts;

        const columns = [
            {title: 'ID', dataIndex: 'id', key: 'id'},
            {title: '账号名', dataIndex: 'accName', key: 'accName'},
            {title: '姓名', dataIndex: 'name', key: 'name'},
            {title: '最后登录时间', dataIndex: 'lastLoginTime', key: 'lastLoginTime'},
            {title: '状态', dataIndex: 'state', key: 'state'},
            {
                title: '操作', key: 'action',
                render: (text: string, record: IMgrAccount) => (
                    <div>
                        <Button type="danger" onClick={() => {
                            this.delConfirm(record.id);
                        }}>删除</Button>
                        <Button type="primary" onClick={() => {
                            this.chgAccount(CHG_ACCOUNT + '/' + record.id);
                        }}>修改</Button>
                    </div>
                ),
            },
        ];

        const pagination = {
            total: mgraccounts.length,
            pageSize: 15,
        };

        return (

            <div>
                <Row>
                    <Col span={24}>
                        <Table rowKey="id" pagination={pagination} columns={columns} dataSource={mgraccounts}
                               size="small"/>
                    </Col>
                </Row>

                <Row>
                    <Col span={24}>
                        <Button type="primary" onClick={this.switch2Add}>添加新帐号</Button>
                    </Col>
                </Row>
            </div>
        );
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(AccMgr);