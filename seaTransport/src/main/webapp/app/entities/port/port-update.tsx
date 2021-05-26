import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './port.reducer';
import { IPort } from 'app/shared/model/port.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IPortUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const PortUpdate = (props: IPortUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { portEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/port');
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
        ...portEntity,
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
          <h2 id="seaTransportApp.port.home.createOrEditLabel" data-cy="PortCreateUpdateHeading">
            Create or edit a Port
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : portEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="port-id">ID</Label>
                  <AvInput id="port-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="loadingPortLatitudeLabel" for="port-loadingPortLatitude">
                  Loading Port Latitude
                </Label>
                <AvField
                  id="port-loadingPortLatitude"
                  data-cy="loadingPortLatitude"
                  type="string"
                  className="form-control"
                  name="loadingPortLatitude"
                  validate={{
                    required: { value: true, errorMessage: 'This field is required.' },
                    min: { value: 0, errorMessage: 'This field should be at least 0.' },
                    number: { value: true, errorMessage: 'This field should be a number.' },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="loadingPortLongitudeLabel" for="port-loadingPortLongitude">
                  Loading Port Longitude
                </Label>
                <AvField
                  id="port-loadingPortLongitude"
                  data-cy="loadingPortLongitude"
                  type="string"
                  className="form-control"
                  name="loadingPortLongitude"
                  validate={{
                    required: { value: true, errorMessage: 'This field is required.' },
                    min: { value: 0, errorMessage: 'This field should be at least 0.' },
                    number: { value: true, errorMessage: 'This field should be a number.' },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="shipmentPortLatitudeLabel" for="port-shipmentPortLatitude">
                  Shipment Port Latitude
                </Label>
                <AvField
                  id="port-shipmentPortLatitude"
                  data-cy="shipmentPortLatitude"
                  type="string"
                  className="form-control"
                  name="shipmentPortLatitude"
                  validate={{
                    required: { value: true, errorMessage: 'This field is required.' },
                    min: { value: 0, errorMessage: 'This field should be at least 0.' },
                    number: { value: true, errorMessage: 'This field should be a number.' },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="shipmentPortLongitudeLabel" for="port-shipmentPortLongitude">
                  Shipment Port Longitude
                </Label>
                <AvField
                  id="port-shipmentPortLongitude"
                  data-cy="shipmentPortLongitude"
                  type="string"
                  className="form-control"
                  name="shipmentPortLongitude"
                  validate={{
                    required: { value: true, errorMessage: 'This field is required.' },
                    min: { value: 0, errorMessage: 'This field should be at least 0.' },
                    number: { value: true, errorMessage: 'This field should be a number.' },
                  }}
                />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/port" replace color="info">
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
  portEntity: storeState.port.entity,
  loading: storeState.port.loading,
  updating: storeState.port.updating,
  updateSuccess: storeState.port.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PortUpdate);
