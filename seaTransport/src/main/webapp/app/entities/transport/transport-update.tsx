import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './transport.reducer';
import { ITransport } from 'app/shared/model/transport.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITransportUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TransportUpdate = (props: ITransportUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { transportEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/transport');
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
        ...transportEntity,
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
          <h2 id="seaTransportApp.transport.home.createOrEditLabel" data-cy="TransportCreateUpdateHeading">
            Create or edit a Transport
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : transportEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="transport-id">ID</Label>
                  <AvInput id="transport-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="transportNameLabel" for="transport-transportName">
                  Transport Name
                </Label>
                <AvField
                  id="transport-transportName"
                  data-cy="transportName"
                  type="text"
                  name="transportName"
                  validate={{
                    required: { value: true, errorMessage: 'This field is required.' },
                    maxLength: { value: 50, errorMessage: 'This field cannot be longer than 50 characters.' },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="maxWeightLabel" for="transport-maxWeight">
                  Max Weight
                </Label>
                <AvField
                  id="transport-maxWeight"
                  data-cy="maxWeight"
                  type="string"
                  className="form-control"
                  name="maxWeight"
                  validate={{
                    required: { value: true, errorMessage: 'This field is required.' },
                    min: { value: 0, errorMessage: 'This field should be at least 0.' },
                    number: { value: true, errorMessage: 'This field should be a number.' },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="speedLabel" for="transport-speed">
                  Speed
                </Label>
                <AvField
                  id="transport-speed"
                  data-cy="speed"
                  type="string"
                  className="form-control"
                  name="speed"
                  validate={{
                    required: { value: true, errorMessage: 'This field is required.' },
                    min: { value: 0, errorMessage: 'This field should be at least 0.' },
                    number: { value: true, errorMessage: 'This field should be a number.' },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="deckSizeLabel" for="transport-deckSize">
                  Deck Size
                </Label>
                <AvField
                  id="transport-deckSize"
                  data-cy="deckSize"
                  type="string"
                  className="form-control"
                  name="deckSize"
                  validate={{
                    required: { value: true, errorMessage: 'This field is required.' },
                    min: { value: 0, errorMessage: 'This field should be at least 0.' },
                    number: { value: true, errorMessage: 'This field should be a number.' },
                  }}
                />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/transport" replace color="info">
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
  transportEntity: storeState.transport.entity,
  loading: storeState.transport.loading,
  updating: storeState.transport.updating,
  updateSuccess: storeState.transport.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TransportUpdate);
