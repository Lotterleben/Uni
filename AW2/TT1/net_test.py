import random
import subprocess
import os
import time

routers = [22,17,16,27,25,15,3,29,20,2]
iperf_servers = [(1,8001), (27, 5001), (17, 8001)]

ping_str = "ping -c 3 10.0.0."
iperf_str = "iperf -u -c 10.0.0.%i -p %i"

def run_command(command):

    proc = subprocess.Popen(command, stdout=subprocess.PIPE, shell=True)
    (out, err) = proc.communicate()

    print "errors:", err
    print "program output:", out

def ping_random_router():
    router = random.choice(routers)
    command = ping_str+ str(router)
    run_command(command)

def iperf_random_router():
    (ip, port) = random.choice(iperf_servers)
    command = iperf_str % (ip, port)
    run_command(command)

if __name__ == "__main__":
    print "xoxoxoxo pinging routers..."
    for i in range (0,25):
        ping_random_router()
        time.sleep(5)
        i+=1

    print "xoxoxoxo checking iperf servers..."
    for i in range (0,20):
        iperf_random_router()
        time.sleep(5)
        i+=1