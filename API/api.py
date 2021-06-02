from flask import Flask, jsonify
from numpy.core.fromnumeric import squeeze
import csv
import pandas as pd

#membaca data yang ada pada csv
data = pd.read_csv("result.csv")
data_list = data.to_dict('records')
#print(type(data_list))

#ambil semua nama tempat
daftar_tempat = [{"id" : 0, "nama_tempat" : "", "id_kategori" : 0, "ratings" : 0}];
id = 1;
for record in data_list:
    for i in range(len(daftar_tempat)):
        if daftar_tempat[i].get("nama_tempat") == record.get("nama_tempat"):
            break
        if(i == len(daftar_tempat) - 1):
            temp = {"id": id, "nama_tempat": record.get("nama_tempat"), "id_kategori": record.get("id_kategori"), "ratings": 0}
            id = id + 1

            #memperoleh ratings
            jumlah_star = 0;
            jumlah_baris = 0;
            for r in data_list:
                if r.get("nama_tempat") == temp.get("nama_tempat"):
                    jumlah_baris = jumlah_baris + 1
                    if int(r.get("star")) == 1:
                        jumlah_star = jumlah_star + 1
            ratings = (jumlah_star / jumlah_baris) * 100
            temp["ratings"] = ratings
            daftar_tempat.append(temp)
daftar_tempat.pop(0)

# csv_file = open("wisata.csv", encoding="utf8")
# read_result = csv.DictReader(csv_file)

# daftar_tempat = list(read_result)

for i in range(len(daftar_tempat)):
    daftar_tempat[i] = dict(daftar_tempat[i])

def getByCategory(param):
    result = []
    for val in daftar_tempat:
        if val.get("id_kategori") == param:
            result.append(val)
    return result

app = Flask(__name__)

@app.route('/')
def index():
    return "Welcome"

@app.route('/wisata', methods=['GET'])
def get():
    return jsonify({'Wisata' : daftar_tempat})


@app.route('/wisata/category/<int:category>', methods=['GET'])
def get_cat(category):
    result = getByCategory(category);
    return jsonify({"wisata" : result})


@app.route('/wisata/<int:id>', methods=['GET'])
def get_id(id):
    return jsonify({'Wisata': daftar_tempat[id]})

if __name__ == "__main__":
    app.run(debug=True)
