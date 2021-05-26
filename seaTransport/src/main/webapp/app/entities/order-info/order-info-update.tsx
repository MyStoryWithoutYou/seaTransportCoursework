import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './order-info.reducer';
import { IOrderInfo } from 'app/shared/model/order-info.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IOrderInfoUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const OrderInfoUpdate = (props: IOrderInfoUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { orderInfoEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/order-info');
  };

  useEffect(() => {
    if (!isNew) {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...orderInfoEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="seaTransportApp.orderInfo.home.createOrEditLabel" data-cy="OrderInfoCreateUpdateHeading">
            Create or edit a OrderInfo
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : orderInfoEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="order-info-id">ID</Label>
                  <AvInput id="order-info-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="orderLabel" for="order-info-order">
                  Order
                </Label>
                <AvField
                  id="order-info-order"
                  data-cy="order"
                  type="string"
                  className="form-control"
                  name="order"
                  validate={{
                    required: { value: true, errorMessage: 'This field is required.' },
                    min: { value: 0, errorMessage: 'This field should be at least 0.' },
                    number: { value: true, errorMessage: 'This field should be a number.' },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="lengthLabel" for="order-info-length">
                  Length
                </Label>
                <AvField
                  id="order-info-length"
                  data-cy="length"
                  type="string"
                  className="form-control"
                  name="length"
                  validate={{
                    required: { value: true, errorMessage: 'This field is required.' },
                    min: { value: 0, errorMessage: 'This field should be at least 0.' },
                    number: { value: true, errorMessage: 'This field should be a number.' },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="widthLabel" for="order-info-width">
                  Width
                </Label>
                <AvField
                  id="order-info-width"
                  data-cy="width"
                  type="string"
                  className="form-control"
                  name="width"
                  validate={{
                    required: { value: true, errorMessage: 'This field is required.' },
                    min: { value: 0, errorMessage: 'This field should be at least 0.' },
                    number: { value: true, errorMessage: 'This field should be a number.' },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="heightLabel" for="order-info-height">
                  Height
                </Label>
                <AvField
                  id="order-info-height"
                  data-cy="height"
                  type="string"
                  className="form-control"
                  name="height"
                  validate={{
                    required: { value: true, errorMessage: 'This field is required.' },
                    min: { value: 0, errorMessage: 'This field should be at least 0.' },
                    number: { value: true, errorMessage: 'This field should be a number.' },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="volumeLabel" for="order-info-volume">
                  Volume
                </Label>
                <AvField
                  id="order-info-volume"
                  data-cy="volume"
                  type="string"
                  className="form-control"
                  name="volume"
                  validate={{
                    required: { value: true, errorMessage: 'This field is required.' },
                    min: { value: 0, errorMessage: 'This field should be at least 0.' },
                    number: { value: true, errorMessage: 'This field should be a number.' },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="weightLabel" for="order-info-weight">
                  Weight
                </Label>
                <AvField
                  id="order-info-weight"
                  data-cy="weight"
                  type="string"
                  className="form-control"
                  name="weight"
                  validate={{
                    required: { value: true, errorMessage: 'This field is required.' },
                    min: { value: 0, errorMessage: 'This field should be at least 0.' },
                    number: { value: true, errorMessage: 'This field should be a number.' },
                  }}
                />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/order-info" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  orderInfoEntity: storeState.orderInfo.entity,
  loading: storeState.orderInfo.loading,
  updating: storeState.orderInfo.updating,
  updateSuccess: storeState.orderInfo.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(OrderInfoUpdate);
