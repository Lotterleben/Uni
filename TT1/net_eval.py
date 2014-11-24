import argparse
import re
import traceback
import sys
import pprint

import pretty_plots

''''
TODO:
_ I think I'm losing one data set..
- mean rather than average, with those nifty little charts (boxplots, I think)! if I can do it in time... (use numpy?)

 TTL exceeded -> routing loop

average:
- packet loss
- delay
- jitter

comparison with voip minimum quality:
how often failed?

'''
infotype = "ping"
curr_info = {}
ping_info = []

def get_ping_info(line):
    global curr_info, ping_info

    if ("errors" in line):
        ping_info.append(curr_info)
        curr_info = {"req_to" : 0}

        errors = line.split(": ")[1]
        if (not "None" in errors):
            print "ERROR: ", errors
    elif ("program output:" in line):
        info = line.split(" ") # yeah, could be regex'd, but what the hell.
        curr_info["ip"] = info[4]
        curr_info["bytes"] = info[6]

    elif("Destination Port Unreachable" in line):
        increment_value(curr_info, "dest_unr")

    elif ("Time to live exceeded" in line):
        increment_value(curr_info, "TTL_Exc")

    elif("Request timeout" in line):
        increment_value(curr_info, "req_to")

    elif("packets transmitted" in line):
        (successes, pkt_loss) = re.search("packets transmitted, (.) packets received, (.*)% packet loss", line).groups()
        curr_info["successes"] = successes
        curr_info["pkt_loss"] = pkt_loss

    elif ("round-trip min/avg/max/stddev" in line):
        (min_, avg_, max_, stddev_) = re.search("round-trip min/avg/max/stddev = (.*)/(.*)/(.*)/(.*) ms", line).groups()
        round_trip = {}
        round_trip["min"] = min_
        round_trip["avg"] = avg_
        round_trip["max"] = max_
        round_trip["stddev"] = stddev_
        curr_info["round_trip"] = round_trip

def get_iperf_info(line):
    pass

def increment_value(dict_, key):
    try:
        dict_[key] += 1
    except:
        curr_info[key] = 1

def get_successful_pings():
    return [info for info in ping_info[1:] if info['successes']!='0']

def get_avg_rtt():
    successful_pings = get_successful_pings()
    # not sure of the math is right here o.o
    all_rtts = [float(info['round_trip']['avg']) for info in successful_pings]
    # TODO: google how to compute average whe i have internet again

def get_max_rtt():
    successful_pings = get_successful_pings()
    max_rtts = [float(info['round_trip']['max']) for info in successful_pings]
    return max(max_rtts)

def get_packet_losses():
    return [float(info['pkt_loss']) for info in ping_info[1:]]

def get_avg_pkt_loss():
    pkt_losses = get_packet_losses()
    #TODO: again, google avg & return

def get_max_pkt_loss():
    # aution: bullshit right now because pkt_losses contains strings, not floats
    pkt_losses = get_packet_losses()
    return max(pkt_losses)

def get_req_timeouts():
    return [info['req_to'] for info in ping_info[1:]]

def get_avg_req_timeouts():
    req_timouts = get_req_timeouts()
    #TODO: again, google avg & return

def eval_capture(file_str):
    global infotype, curr_info, ping_info
    try:
        f = open(file_str)
    except:
        traceback.print_exc(file=sys.stdout)
        return

    curr_info = {}
    ping_info = []

    for line in f:
        if ("xoxoxoxo pinging routers..." in line):
            infotype = "ping"
        elif ("xoxoxoxo checking iperf servers..." in line):
            infotype = "iperf"

        elif (infotype == "ping"):
            get_ping_info(line)
        elif (infotype == "iperf"):
            get_iperf_info(line)

    print "xxxx ping stats xxxxxx"
    #pp = pprint.PrettyPrinter(indent=2)
    #pp.pprint(ping_info)

    #print ping_info
    print "average round trip time: ", get_avg_rtt()
    print "max round trip time: ", get_max_rtt()
    print "average packet loss: ", get_avg_pkt_loss()
    print "max packet loss: ", get_max_pkt_loss()
    print "average request timeouts: ", get_avg_req_timeouts()
    print "\n"

    return {"packet_losses": get_packet_losses(),
            "req_timeouts": get_req_timeouts()}

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description='eval captures')
    parser.add_argument('-o','--olsr_log', type=str,required=True, help='olsr logfile')
    parser.add_argument('-b','--batman_log', type=str,required=True, help='batman logfile')

    args = parser.parse_args()

    olsr_data = eval_capture(args.olsr_log)
    batman_data = eval_capture(args.batman_log)

    print "packet loss graph:"
    #pretty_plots.plot_dots(("OLSR","BATMAN"),olsr_data["packet_losses"],
    #                        batman_data["packet_losses"], "packet loss in %", "time in 5 sec-intervals")

    print("done!")