import * as React from 'react';
import { ChangeEvent } from 'react';
import { connect } from 'react-redux';
import { match } from 'react-router';
import { bindActionCreators, Dispatch } from 'redux';
import { Button, Col, Form, Input, Row } from 'antd';
import { History } from 'history';

import { chgPlatform, markCreateOrModifyPlatform } from '../../../sagas/serverMgr/platformMgr';
import { ICombineState } from '../../../redux/reducer';
import { PLATFORM } from '../../../const/funcType';
import { IMarkCreateModifyPlatform, IPlatform } from '../../../redux/modules/serverMgr/platform';
import { IReqAction } from '../../../sagas/ibaseaction';

const FormItem = Form.Item;

interface IChgPlatformParam {
    pltid: string;
}

interface IStateProps {
    history: History;
    match: match<IChgPlatformParam>;
    platforms: IPlatform[];
    atCreatingOrModifying: boolean;
}

interface IDispatchProps {
    markCreateOrModifyPlatform(atCreatingOrModifying: boolean): IMarkCreateModifyPlatform;
    chgPlatform(data: {}): IReqAction<{}>;
}

type IAppProps = IStateProps & IDispatchProps;

function mapStateToProps(state: ICombineState) {
    return {
        platforms: state.serverMgr.platform.platforms,
        atCreatingOrModifying: state.serverMgr.platform.atCreatingOrModifying,
    };
}

const mapDispatchToProps = (dispatch: Dispatch<{}>) => {
    return bindActionCreators({
        markCreateOrModifyPlatform,
        chgPlatform,
    }, dispatch);
};

interface IState {
    targetPlatOpId: number;
    targetPlatform: string;
    targetPlatformId: number;
    targetPlatgameId: number;
}

class ChgPlatform extends React.Component<IAppProps, IState> {

    constructor(props: IAppProps) {
        super(props);

        const targetPlatformId = parseInt(this.props.match.params.pltid, 10);
        let targetPlatform = '';
        let targetPlatOpId = 0;
        let targetPlatgameId = 0;
        this.props.platforms.map((platform: IPlatform) => {
            if (platform.id === targetPlatformId) {
                targetPlatOpId = platform.opId;
                targetPlatform = platform.platformName;
                targetPlatgameId = platform.gameId;
            }
        });

        this.state = {
            targetPlatOpId,
            targetPlatform,
            targetPlatformId,
            targetPlatgameId,
        };

        this.props.markCreateOrModifyPlatform(true);
    }

    componentWillReceiveProps(nextProps: IAppProps) {
        if (!nextProps.atCreatingOrModifying && this.props.atCreatingOrModifying) {
            this.props.history.push(PLATFORM);
        }
    }

    cancelAdd = () => {
        this.props.markCreateOrModifyPlatform(false);
    };

    handleSubmit = () => {
        const data = {
            id: this.state.targetPlatformId,
            opId: this.state.targetPlatOpId,
            platformName: this.state.targetPlatform,
            gameId: this.state.targetPlatgameId,
        };
        this.props.chgPlatform(data);
    };

    updatePlatname = (event: ChangeEvent<HTMLInputElement>) => {
        const platname = event.target.value;
        this.setState({
            targetPlatform: platname,
        });
    };

    updatePlatFormOpId = (event: ChangeEvent<HTMLInputElement>) => {
        const opId = parseInt(event.target.value, 10);
        this.setState({
            targetPlatOpId: opId,
        });
    };

    updatePlatFormGameId = (event: ChangeEvent<HTMLInputElement>) => {
        const gameId = parseInt(event.target.value, 10);
        this.setState({
            targetPlatgameId: gameId,
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

        return (
            <Row>
                <Col span={24}>
                    <Form>
                        <FormItem {...formItemLayout} label="ID">
                            {this.state.targetPlatformId}
                        </FormItem>
                        <FormItem {...formItemLayout} label="平台名称">
                            <Input type="text" value={this.state.targetPlatform}
                                   onChange={this.updatePlatname}/>
                        </FormItem>
                        <FormItem {...formItemLayout} label="opId">
                            <Input type="number" value={this.state.targetPlatOpId}
                                   onChange={this.updatePlatFormOpId}/>
                        </FormItem>
                        <FormItem {...formItemLayout} label="gameId">
                            <Input type="number" value={this.state.targetPlatgameId}
                                   onChange={this.updatePlatFormGameId}/>
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

export default connect(mapStateToProps, mapDispatchToProps)(ChgPlatform);