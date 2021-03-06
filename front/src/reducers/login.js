import * as types from '../actions/ActionTypes';

const initialState = {
    isLoggedIn: false,
    fetchingUpdate: false,
    user: {}
}

export default function login(state=initialState, action) {
    switch (action.type) {
        case types.LOGIN_REQUEST:
            return {
                ...state,
                fetchingUpdate: true
            }

        case types.LOGIN_SUCCESS:
            return {
                ...state,
                isLoggedIn: true,
                fetchingUpdate: false,
                user: action.user
            };

        case types.LOGIN_FAILURE:
            return {
                ...state,
                fetchingUpdate: false
            }

        case types.LOGOUT_REQUEST:
            return {
                ...state,
                fetchingUpdate: true
            }

        case types.LOGOUT_SUCCESS:
            return {
                ...state,
                fetchingUpdate: false,
                isLoggedIn: false,
                user: {}
            }

        case types.LOGOUT_FAILURE:
            return {
                ...state,
                fetchingUpdate: false
            }

        default:
            return state;
    }
}
