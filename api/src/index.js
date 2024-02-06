const express = require("express");
const admin = require('firebase-admin/app');
const { getAppCheck } = require('firebase-admin/app-check')
const serviceAccount = require('../serviceAccount.json')

const app = express();
const firebaseApp = admin.initializeApp({
    credential: admin.cert(serviceAccount)
});

const appCheckVerification = async (req, res, next) => {
    const appCheckToken = req.header("X-Firebase-AppCheck");

    if (!appCheckToken) {
        res.status(401);
        return next("Unauthorized");
    }

    try {
        const appCheckClaims = await getAppCheck().verifyToken(appCheckToken, { consume: true });

        // If verifyToken() succeeds, continue with the next middleware
        // function in the stack.
        return next();
    } catch (err) {
        console.log(err)
        res.status(401);
        return next("Unauthorized");
    }
}

app.get("/photos", [appCheckVerification], (req, res) => {
    const response = {
        url: 'https://images.dog.ceo/breeds/terrier-border/n02093754_2276.jpg'
    }

    console.log(`finished ${new Date()}`)
    res.json(response);
});


app.listen(3000, () => console.log('Started in 3000'))