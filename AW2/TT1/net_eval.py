import argparse
import re
import traceback
import sys
import pprint

''''
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
        curr_info = {}

        errors = line.split(": ")[1]
        if (not "None" in errors):
            print "ERROR: ", errors
    elif ("program output:" in line):
        info = line.split(" ") # yeah, could be regex'd, but what the hell.
        curr_info["ip"] = info[4]
        curr_info["bytes"] = info[6]

    elif("Destination Port Unreachable" in line):
        try:
            curr_info["dest_unr"] += 1
        except:
            curr_info["dest_unr"] = 1

    elif ("Time to live exceeded" in line):
        try:
            curr_info["TTL_exc"] += 1
        except:
            curr_info["TTL_exc"] = 1

    elif("Request timeout" in line):
        try:
            curr_info["req_to"] += 1
        except:
            curr_info["req_to"] = 1

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

def eval_capture(file_str):
    global infotype
    try:
        f = open(file_str)
    except:
        #print("Error: couldn't find file. Aborting.", file=sys.stderr)
        print "xoxoxo"
        traceback.print_exc(file=sys.stdout)
        return

    for line in f:
        if ("xoxoxoxo pinging routers..." in line):
            infotype = "ping"
        elif ("xoxoxoxo checking iperf servers..." in line):
            infotype = "iperf"

        elif (infotype == "ping"):
            get_ping_info(line)
        elif (infotype == "iperf"):
            get_iperf_info(line)

    pp = pprint.PrettyPrinter(indent=2)
    pp.pprint(ping_info)

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description='eval captures')
    parser.add_argument('-f','--file', type=str,required=True, help='olsr/batman log')
    args = parser.parse_args()
    eval_capture(args.file)

    print("done!")