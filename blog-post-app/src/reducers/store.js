import { createStore, combineReducers } from 'redux';
import blogReducer from './blogReducer';

const rootReducer = combineReducers({
  blog: blogReducer,
});

const store = createStore(rootReducer);

export default store;