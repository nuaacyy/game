import * as React from 'react';
import { connect } from 'react-redux';
import { bindActionCreators, Dispatch } from 'redux';

import { ICombineState } from '../../../redux/reducer';

interface IStateProps {
}

interface IDispatchProps {
}

type IAppProps = IStateProps & IDispatchProps;

function mapStateToProps(state: ICombineState) {
    return {};
}

const mapDispatchToProps = (dispatch: Dispatch<{}>) => {
    return bindActionCreators({}, dispatch);
};

interface IState {

}

class MainPanel extends React.Component<IAppProps, IState> {

    constructor(props: IAppProps) {
        super(props);

        this.state = {};
    }

    render() {
        return (
            <div>
                <h1>汇总</h1>
            </div>
        );
    }

}

export default connect(mapStateToProps, mapDispatchToProps)(MainPanel);