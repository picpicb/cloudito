const Prometheus = require('prom-client');

module.exports = {
    GetMap:new Prometheus.Counter({
        name: 'total_get_map',
        help: 'Total number of get map',
        labelNames: ['get_map']
    }),
}