import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './customer.reducer';
import { ICustomer } from 'app/shared/model/customer.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ICustomerUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const CustomerUpdate = (props: ICustomerUpdateProps) => {
  const [isNew] = useState(!props.match.params || !props.match.params.id);

  const { customerEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/customer');
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
        ...customerEntity,
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
          <h2 id="seaTransportApp.customer.home.createOrEditLabel" data-cy="CustomerCreateUpdateHeading">
            Create or edit a Customer
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : customerEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="customer-id">ID</Label>
                  <AvInput id="customer-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="organizationNameLabel" for="customer-organizationName">
                  Organization Name
                </Label>
                <AvField
                  id="customer-organizationName"
                  data-cy="organizationName"
                  type="text"
                  name="organizationName"
                  validate={{
                    required: { value: true, errorMessage: 'This field is required.' },
                    maxLength: { value: 50, errorMessage: 'This field cannot be longer than 50 characters.' },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="contactPersonLabel" for="customer-contactPerson">
                  Contact Person
                </Label>
                <AvField
                  id="customer-contactPerson"
                  data-cy="contactPerson"
                  type="text"
                  name="contactPerson"
                  validate={{
                    required: { value: true, errorMessage: 'This field is required.' },
                    maxLength: { value: 40, errorMessage: 'This field cannot be longer than 40 characters.' },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="countryLabel" for="customer-country">
                  Country
                </Label>
                <AvField
                  id="customer-country"
                  data-cy="country"
                  type="text"
                  name="country"
                  validate={{
                    required: { value: true, errorMessage: 'This field is required.' },
                    maxLength: { value: 30, errorMessage: 'This field cannot be longer than 30 characters.' },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="contactPhoneLabel" for="customer-contactPhone">
                  Contact Phone
                </Label>
                <AvField
                  id="customer-contactPhone"
                  data-cy="contactPhone"
                  type="text"
                  name="contactPhone"
                  validate={{
                    required: { value: true, errorMessage: 'This field is required.' },
                    maxLength: { value: 20, errorMessage: 'This field cannot be longer than 20 characters.' },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="emailLabel" for="customer-email">
                  Email
                </Label>
                <AvField
                  id="customer-email"
                  data-cy="email"
                  type="text"
                  name="email"
                  validate={{
                    required: { value: true, errorMessage: 'This field is required.' },
                    maxLength: { value: 30, errorMessage: 'This field cannot be longer than 30 characters.' },
                  }}
                />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/customer" replace color="info">
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
  customerEntity: storeState.customer.entity,
  loading: storeState.customer.loading,
  updating: storeState.customer.updating,
  updateSuccess: storeState.customer.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(CustomerUpdate);
