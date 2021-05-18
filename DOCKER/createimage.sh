#!/bin/bash
cd ../backend/src/main/resources/static/
rm -r new/
mkdir new
cd ../../../../../frontend/
npm install
ng build --prod --base-href="/new/"
cp -r dist/frontend ../backend/src/main/resources/static/new/