import json
from pprint import pprint

# 获取当前版本
current_version = ''
with open('./version.json', 'r') as f:
    json_data = json.load(f)
    current_version = json_data['dbCurrentVersion']
    print(current_version)