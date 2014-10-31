# Protokoll 31.10.14
von: Alex & Lotte

## ping
Um die generelle Erreichbarkeit der Knoten zu testen, haben wir unächst ping benutzt.

### letzte Reihe
    PING 10.0.0.22 (10.0.0.22): 56 data bytes
    64 bytes from 10.0.0.22: icmp_seq=0 ttl=63 time=4.761 ms
    64 bytes from 10.0.0.22: icmp_seq=1 ttl=63 time=6.940 ms
    64 bytes from 10.0.0.22: icmp_seq=2 ttl=63 time=9.151 ms
    64 bytes from 10.0.0.22: icmp_seq=3 ttl=63 time=8.253 ms
    64 bytes from 10.0.0.22: icmp_seq=4 ttl=63 time=12.171 ms
    64 bytes from 10.0.0.22: icmp_seq=5 ttl=63 time=11.451 ms
    ^C
    --- 10.0.0.22 ping statistics ---
    6 packets transmitted, 6 packets received, 0.0% packet loss
    round-trip min/avg/max/stddev = 4.761/8.788/12.171/2.536 ms

### vorletzte (unsere) Reihe

    frankenbook-pro:~ lotte$ ping 10.0.0.18
    PING 10.0.0.18 (10.0.0.18): 56 data bytes
    64 bytes from 10.0.0.18: icmp_seq=0 ttl=62 time=9.146 ms
    64 bytes from 10.0.0.18: icmp_seq=1 ttl=62 time=7.354 ms
    64 bytes from 10.0.0.18: icmp_seq=2 ttl=62 time=8.587 ms
    64 bytes from 10.0.0.18: icmp_seq=3 ttl=62 time=8.798 ms
    ^C
    --- 10.0.0.18 ping statistics ---
    4 packets transmitted, 4 packets received, 0.0% packet loss
    round-trip min/avg/max/stddev = 7.354/8.471/9.146/0.675 ms


### zweite Reihe

    ^C^Cfrankenbook-pro:~ lotte$ ping 10.0.0.25
    PING 10.0.0.25 (10.0.0.25): 56 data bytes
    ^C^Cfrankenbook-pro:~ lotte$ ping 10.0.0.15
    PING 10.0.0.15 (10.0.0.15): 56 data bytes
    Request timeout for icmp_seq 0
    Request timeout for icmp_seq 1
    Request timeout for icmp_seq 2
    Request timeout for icmp_seq 3
    Request timeout for icmp_seq 4
    Request timeout for icmp_seq 5
    Request timeout for icmp_seq 6
    ^C

### erste Reihe

    PING 10.0.0.20 (10.0.0.20): 56 data bytes
    64 bytes from 10.0.0.20: icmp_seq=0 ttl=61 time=10.405 ms
    64 bytes from 10.0.0.20: icmp_seq=1 ttl=61 time=23.810 ms
    64 bytes from 10.0.0.20: icmp_seq=2 ttl=61 time=15.159 ms

..

    traceroute to 10.0.0.20 (10.0.0.20), 64 hops max, 52 byte packets
     1  192.168.3.1 (192.168.3.1)  5.117 ms  12.382 ms  4.774 ms
     2  10.0.0.22 (10.0.0.22)  3.672 ms *
        10.0.0.27 (10.0.0.27)  13.976 ms
     3  10.0.0.18 (10.0.0.18)  11.622 ms  9.336 ms  5.353 ms
     4  10.0.0.20 (10.0.0.20)  5.537 ms  2152.211 ms *

## iperf messungen

### server einrichten

    ???

### client

    syntax: ``iperf -u -c <server ip> -p <server port>``

#### mit vorderem iperf server verbunden

    frankenbook-pro:~ lotte$ iperf -u -c 10.0.0.1 -p 8001
    ------------------------------------------------------------
    Client connecting to 10.0.0.1, UDP port 8001
    Sending 1470 byte datagrams
    UDP buffer size: 9.00 KByte (default)
    ------------------------------------------------------------
    [  4] local 192.168.3.112 port 60835 connected with 10.0.0.1 port 8001
    [ ID] Interval       Transfer     Bandwidth
    [  4]  0.0-10.0 sec  1.25 MBytes  1.05 Mbits/sec
    [  4] Sent 893 datagrams
    [  4] Server Report:
    [  4]  0.0-10.0 sec  1.25 MBytes  1.05 Mbits/sec   4.203 ms    2/  893 (0.22%)



