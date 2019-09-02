# springboot-distribution-redis
a simple demo for springboot with distribution locker based on redis
you can run two applications at the same time(one port for 8080, another for 8081), and call the 'localhost:{port}/call' url, you can see
different logs, and the distributed locker only can be used by one application at the same time.
