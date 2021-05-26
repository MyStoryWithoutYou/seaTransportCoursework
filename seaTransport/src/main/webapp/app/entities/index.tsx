import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Status from './status';
import Transport from './transport';
import Order from './order';
import OrderInfo from './order-info';
import Port from './port';
import Customer from './customer';
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}status`} component={Status} />
      <ErrorBoundaryRoute path={`${match.url}transport`} component={Transport} />
      <ErrorBoundaryRoute path={`${match.url}order`} component={Order} />
      <ErrorBoundaryRoute path={`${match.url}order-info`} component={OrderInfo} />
      <ErrorBoundaryRoute path={`${match.url}port`} component={Port} />
      <ErrorBoundaryRoute path={`${match.url}customer`} component={Customer} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
