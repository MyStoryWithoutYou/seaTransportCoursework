import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './port.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IPortDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const PortDetail = (props: IPortDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { portEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="portDetailsHeading">Port</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{portEntity.id}</dd>
          <dt>
            <span id="loadingPortLatitude">Loading Port Latitude</span>
          </dt>
          <dd>{portEntity.loadingPortLatitude}</dd>
          <dt>
            <span id="loadingPortLongitude">Loading Port Longitude</span>
          </dt>
          <dd>{portEntity.loadingPortLongitude}</dd>
          <dt>
            <span id="shipmentPortLatitude">Shipment Port Latitude</span>
          </dt>
          <dd>{portEntity.shipmentPortLatitude}</dd>
          <dt>
            <span id="shipmentPortLongitude">Shipment Port Longitude</span>
          </dt>
          <dd>{portEntity.shipmentPortLongitude}</dd>
        </dl>
        <Button tag={Link} to="/port" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/port/${portEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ port }: IRootState) => ({
  portEntity: port.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(PortDetail);
