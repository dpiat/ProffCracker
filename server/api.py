## -*- coding: utf-8 -*-
import json
import sqlite3
import requests

with open("professions.json", "r", encoding="utf-8") as read_file:
    data = json.load(read_file)

s = requests.Session()


def get_info_vk(access_token):
    url = "https://api.vk.com/method/groups.get?extended=1&fields=description&access_token=" + access_token + "&v=5.80"
    resp = s.get(url).json()
    if "error" in resp:
        return False
    return resp


def get_info_user(access_token):
    resp = get_info_vk(access_token)
    if not resp:
        default = {0: 'Yurist', 1: 'Sports', 2: 'Geography', 3: 'Military', 4: 'It', 5: 'Medical', 6: 'Music', 7: 'Psychology', 8: 'Mechanic'}
        return default
    groups = resp['response']['items']
    temp = {
        "IT": 0,
        "Medicine": 0,
        "Nentrepreneurship": 0,
        "Physics": 0,
        "Philology": 0,
        "History": 0
    }
    for item in groups:
        description = item['description'].lower()
        for key in data:
            for keyWords in data[key]['key_words']:
                if description.find(keyWords) != -1:
                    temp[key] += 1
                    break
    temp = list(temp.items())
    responce = {
        "code": "1",
        "professions": {

        }
    }
    temp.sort(key=lambda i: i[1], reverse=True)
    for i in range(0, len(temp)):
        prof = {
            "name": temp[i][0],
            "percent": str(int(temp[i][1])/int(resp['response']['count']))[:6:]
        }
        responce["professions"].update({i: prof})
    return responce


def get_event(category):
    conn = sqlite3.connect('database.db')
    cursor = conn.cursor()
    query = "SELECT * FROM professions where category=\"" + str(category) + "\""
    cursor.execute(query)
    results = cursor.fetchall()
    conn.close()
    number = 0
    events = {}
    for row in results:
        tmp = {}
        tmp["title"] = row[0]
        tmp["description"] = row[1]
        tmp["contacts"] = row[2]
        tmp["place"] = row[3]
        tmp["date"] = row[5]
        events[number] = tmp
        number += 1
    return events

def write_event(ivent):
    conn = sqlite3.connect('database.db')
    cursor = conn.cursor()
    req = "('" + ivent["title"] + "', '" + ivent["description"] + "', '" + ivent["contacts"] \
               + "', '" + ivent["place"] + "', '" + ivent['category'] + "', " + ivent["date"] + "')"
    cursor.execute("insert into professions values " + req)
    conn.commit()
    conn.close()
    resp = {
        "code": "1"
    }
    return resp
