import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './transport.reducer';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITransportDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TransportDetail = (props: ITransportDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { transportEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="transportDetailsHeading">Transport</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{transportEntity.id}</dd>
          <dt>
            <span id="transportName">Transport Name</span>
          </dt>
          <dd>{transportEntity.transportName}</dd>
          <dt>
            <span id="maxWeight">Max Weight</span>
          </dt>
          <dd>{transportEntity.maxWeight}</dd>
          <dt>
            <span id="speed">Speed</span>
          </dt>
          <dd>{transportEntity.speed}</dd>
          <dt>
            <span id="deckSize">Deck Size</span>
          </dt>
          <dd>{transportEntity.deckSize}</dd>
        </dl>
        <Button tag={Link} to="/transport" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/transport/${transportEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ transport }: IRootState) => ({
  transportEntity: transport.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TransportDetail);
