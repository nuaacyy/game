import { bindActionCreators, Dispatch } from 'redux';
import * as React from 'react';
import { Col, Row, Table } from 'antd';
import { connect } from 'react-redux';

import { History } from 'history';
import { ICombineState } from '../../../redux/reducer';
import { ICommCfgTopic } from '../../../redux/modules/serverMgr/commCfg';

interface IStateProps {
    history: History;
    topics: ICommCfgTopic[];
}

interface IDispatchProps {

}

type IAppProps = IStateProps & IDispatchProps;

function mapStateToProps(state: ICombineState) {
    return {
        topics: state.serverMgr.commCfg.topics,
    };
}

const mapDispatchToProps = (dispatch: Dispatch<{}>) => {
    return bindActionCreators({

    }, dispatch);
};

interface IState {
    selectId: number;
}

class CommDetail extends React.Component<IAppProps, IState> {

    columns = [
        {title: 'Topic', dataIndex: 'topic', key: 'topic'},
        {title: 'Max分区', dataIndex: 'maxPartitionId', key: 'maxPartitionId'},
    ];

    constructor(props: IAppProps) {
        super(props);

        this.state = {
            selectId: 0,
        };
    }

    render() {
        const topics = this.props.topics;

        return (
            <div>
                <Row>
                    <Col span={24}>
                        <Table rowKey="id" columns={this.columns} dataSource={topics}
                               size="small"/>
                    </Col>
                </Row>
            </div>
        );
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(CommDetail);