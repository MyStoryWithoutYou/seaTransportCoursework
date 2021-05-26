import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './order-info.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IOrderInfoDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const OrderInfoDetail = (props: IOrderInfoDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { orderInfoEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="orderInfoDetailsHeading">OrderInfo</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{orderInfoEntity.id}</dd>
          <dt>
            <span id="order">Order</span>
          </dt>
          <dd>{orderInfoEntity.order}</dd>
          <dt>
            <span id="length">Length</span>
          </dt>
          <dd>{orderInfoEntity.length}</dd>
          <dt>
            <span id="width">Width</span>
          </dt>
          <dd>{orderInfoEntity.width}</dd>
          <dt>
            <span id="height">Height</span>
          </dt>
          <dd>{orderInfoEntity.height}</dd>
          <dt>
            <span id="volume">Volume</span>
          </dt>
          <dd>{orderInfoEntity.volume}</dd>
          <dt>
            <span id="weight">Weight</span>
          </dt>
          <dd>{orderInfoEntity.weight}</dd>
        </dl>
        <Button tag={Link} to="/order-info" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/order-info/${orderInfoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ orderInfo }: IRootState) => ({
  orderInfoEntity: orderInfo.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(OrderInfoDetail);
