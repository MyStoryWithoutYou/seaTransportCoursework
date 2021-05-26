import axios from 'axios';
import {
  parseHeaderForLinks,
  loadMoreDataWhenScrolled,
  ICrudGetAction,
  ICrudGetAllAction,
  ICrudPutAction,
  ICrudDeleteAction,
} from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPort, defaultValue } from 'app/shared/model/port.model';

export const ACTION_TYPES = {
  FETCH_PORT_LIST: 'port/FETCH_PORT_LIST',
  FETCH_PORT: 'port/FETCH_PORT',
  CREATE_PORT: 'port/CREATE_PORT',
  UPDATE_PORT: 'port/UPDATE_PORT',
  PARTIAL_UPDATE_PORT: 'port/PARTIAL_UPDATE_PORT',
  DELETE_PORT: 'port/DELETE_PORT',
  RESET: 'port/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPort>,
  entity: defaultValue,
  links: { next: 0 },
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type PortState = Readonly<typeof initialState>;

// Reducer

export default (state: PortState = initialState, action): PortState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PORT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PORT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_PORT):
    case REQUEST(ACTION_TYPES.UPDATE_PORT):
    case REQUEST(ACTION_TYPES.DELETE_PORT):
    case REQUEST(ACTION_TYPES.PARTIAL_UPDATE_PORT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_PORT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PORT):
    case FAILURE(ACTION_TYPES.CREATE_PORT):
    case FAILURE(ACTION_TYPES.UPDATE_PORT):
    case FAILURE(ACTION_TYPES.PARTIAL_UPDATE_PORT):
    case FAILURE(ACTION_TYPES.DELETE_PORT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_PORT_LIST): {
      const links = parseHeaderForLinks(action.payload.headers.link);

      return {
        ...state,
        loading: false,
        links,
        entities: loadMoreDataWhenScrolled(state.entities, action.payload.data, links),
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    }
    case SUCCESS(ACTION_TYPES.FETCH_PORT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_PORT):
    case SUCCESS(ACTION_TYPES.UPDATE_PORT):
    case SUCCESS(ACTION_TYPES.PARTIAL_UPDATE_PORT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_PORT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/ports';

// Actions

export const getEntities: ICrudGetAllAction<IPort> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_PORT_LIST,
    payload: axios.get<IPort>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<IPort> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PORT,
    payload: axios.get<IPort>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IPort> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PORT,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const updateEntity: ICrudPutAction<IPort> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PORT,
    payload: axios.put(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const partialUpdate: ICrudPutAction<IPort> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.PARTIAL_UPDATE_PORT,
    payload: axios.patch(`${apiUrl}/${entity.id}`, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPort> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PORT,
    payload: axios.delete(requestUrl),
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
