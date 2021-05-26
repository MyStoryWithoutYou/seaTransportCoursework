import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './order.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IOrderDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const OrderDetail = (props: IOrderDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { orderEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="orderDetailsHeading">Order</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{orderEntity.id}</dd>
          <dt>
            <span id="customer">Customer</span>
          </dt>
          <dd>{orderEntity.customer}</dd>
          <dt>
            <span id="port">Port</span>
          </dt>
          <dd>{orderEntity.port}</dd>
          <dt>
            <span id="dateOfLoading">Date Of Loading</span>
          </dt>
          <dd>
            {orderEntity.dateOfLoading ? <TextFormat value={orderEntity.dateOfLoading} type="date" format={APP_LOCAL_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="dateOfShipment">Date Of Shipment</span>
          </dt>
          <dd>
            {orderEntity.dateOfShipment ? (
              <TextFormat value={orderEntity.dateOfShipment} type="date" format={APP_LOCAL_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="status">Status</span>
          </dt>
          <dd>{orderEntity.status}</dd>
          <dt>
            <span id="transport">Transport</span>
          </dt>
          <dd>{orderEntity.transport}</dd>
        </dl>
        <Button tag={Link} to="/order" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/order/${orderEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ order }: IRootState) => ({
  orderEntity: order.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(OrderDetail);
