import json
import urllib
import requests

url = 'https://parseapi.back4app.com/classes/SK?limit=99999999999999990&excludeKeys=admin2,admin2Code,latLong'
headers = {
    'X-Parse-Application-Id': 'yx4wD8txnRVmGVV2L1h4Fc98SGcIVpXA4Zi59A4y', # app id
    'X-Parse-Master-Key': 'jwZirbARd3oN4Wemb66SyTfuEfJzqCjPYhoe9WdC'  # readonly key
}


def main():
    data = readFromFile()
    getData(data)

def apiRequest():
    data = json.loads(requests.get(url, headers=headers).content.decode('utf-8')) # api request
    return data

def writeToFile(data):
    with open('api.json', 'w', encoding='utf-8') as json_file:
        json.dump(data, json_file, indent=2, ensure_ascii=False)

def readFromFile():
    with open('api.json', 'r', encoding='utf-8') as json_file:
        data = json.load(json_file)
    return data

def  getData(data):
    with open('slovakCities.sql', 'w', encoding='utf-8') as textFile:
        for item in data["results"]:
            textFile.write(f'insert into City values ("{item["countryCode"] + "-" + (item["postalCode"]).replace(" ", "")}", "{item["place"]}", 1)' + '\n')

if __name__ == "__main__":
    main()
