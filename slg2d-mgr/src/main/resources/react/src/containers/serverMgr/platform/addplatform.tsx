import * as React from 'react';
import { connect } from 'react-redux';

import { bindActionCreators, Dispatch } from 'redux';
import { Button, Col, Form, Input, Row } from 'antd';
import { History } from 'history';

import { addPlatform, IAddPlatformData, markCreateOrModifyPlatform } from '../../../sagas/serverMgr/platformMgr';
import { ICombineState } from '../../../redux/reducer';
import { PLATFORM } from '../../../const/funcType';
import { ChangeEvent } from 'react';
import { IReqAction } from '../../../sagas/ibaseaction';
import { IMarkCreateModifyPlatform } from '../../../redux/modules/serverMgr/platform';

const FormItem = Form.Item;

interface IStateProps {
    history: History;
    atCreatingOrModifying: boolean;
}

interface IDispatchProps {
    markCreateOrModifyPlatform(atCreatingOrModifying: boolean): IMarkCreateModifyPlatform;
    addPlatform(platName: string, opId: number, gameId: number): IReqAction<IAddPlatformData>;
}

type IAppProps = IStateProps & IDispatchProps;

function mapStateToProps(state: ICombineState) {
    return {
        atCreatingOrModifying: state.serverMgr.platform.atCreatingOrModifying,
    };
}

const mapDispatchToProps = (dispatch: Dispatch<{}>) => {
    return bindActionCreators({
        markCreateOrModifyPlatform,
        addPlatform,
    }, dispatch);
};

interface IState {
    opId: number;
    platFormName: string;
    gameId: number;
}

class AddPlatform extends React.Component<IAppProps, IState> {

    constructor(props: IAppProps) {
        super(props);

        this.state = {
            opId: 0,
            platFormName: '',
            gameId: 0,
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

    updatePlatFormName = (event: ChangeEvent<HTMLInputElement>) => {
        const platFormName = event.target.value;
        this.setState({
            platFormName,
        });
    };

    updatePlatFormOpId = (event: ChangeEvent<HTMLInputElement>) => {
        const opId = parseInt(event.target.value, 10);
        this.setState({
            opId,
        });
    };

    updatePlatFormGameId = (event: ChangeEvent<HTMLInputElement>) => {
        const gameId = parseInt(event.target.value, 10);
        this.setState({
            gameId,
        });
    };

    handleSubmit = () => {
        this.props.addPlatform(this.state.platFormName, this.state.opId, this.state.gameId);
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
                        <FormItem {...formItemLayout} label="平台名称">
                            <Input type="text" value={this.state.platFormName}
                                   onChange={this.updatePlatFormName}/>
                        </FormItem>
                        <FormItem {...formItemLayout} label="opId">
                            <Input type="number" value={this.state.opId}
                                   onChange={this.updatePlatFormOpId}/>
                        </FormItem>
                        <FormItem {...formItemLayout} label="gameId">
                            <Input type="number" value={this.state.gameId}
                                   onChange={this.updatePlatFormGameId}/>
                        </FormItem>
                        <FormItem {...tailFormItemLayout}>
                            <Button type="primary" onClick={this.handleSubmit}>添加</Button>
                            <Button type="primary" onClick={this.cancelAdd}>取消</Button>
                        </FormItem>
                    </Form>
                </Col>
            </Row>
        );
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(AddPlatform);