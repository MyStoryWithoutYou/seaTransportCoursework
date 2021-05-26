import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './status.reducer';
import { IStatus } from 'app/shared/model/status.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IStatusUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const StatusUpdate = (props: IStatusUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { statusEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/status');
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
        ...statusEntity,
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
          <h2 id="seaTransportApp.status.home.createOrEditLabel" data-cy="StatusCreateUpdateHeading">
            Create or edit a Status
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : statusEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="status-id">ID</Label>
                  <AvInput id="status-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="statusNameLabel" for="status-statusName">
                  Status Name
                </Label>
                <AvField
                  id="status-statusName"
                  data-cy="statusName"
                  type="text"
                  name="statusName"
                  validate={{
                    required: { value: true, errorMessage: 'This field is required.' },
                    maxLength: { value: 30, errorMessage: 'This field cannot be longer than 30 characters.' },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="descriptionLabel" for="status-description">
                  Description
                </Label>
                <AvField
                  id="status-description"
                  data-cy="description"
                  type="text"
                  name="description"
                  validate={{
                    required: { value: true, errorMessage: 'This field is required.' },
                    maxLength: { value: 100, errorMessage: 'This field cannot be longer than 100 characters.' },
                  }}
                />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/status" replace color="info">
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
  statusEntity: storeState.status.entity,
  loading: storeState.status.loading,
  updating: storeState.status.updating,
  updateSuccess: storeState.status.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(StatusUpdate);
