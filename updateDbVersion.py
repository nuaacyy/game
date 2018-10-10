import json
import argparse
from pprint import pprint

parser = argparse.ArgumentParser(description='修改版本')
parser.add_argument('-v', dest='version', action='store', help='目标版本')
args = parser.parse_args()

# 更新版本
current_version = ''
with open('./version.json', 'rt') as f:
    json_data = json.load(f)
    current_version = json_data['dbCurrentVersion']
    json_data['dbOldVersion'] = current_version
    json_data['dbCurrentVersion'] = args.version
    pprint(json_data)

    with open('./version.json', 'wt') as ff:
        json.dump(json_data, ff)