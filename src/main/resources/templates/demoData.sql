INSERT INTO WISHLIST (LISTID, WISHERNAME,EVENTNAME) VALUES (1, 'JOAKIM', 'Joakims fødselsdag');
INSERT INTO WISHLIST (LISTID, WISHERNAME,EVENTNAME) VALUES (2, 'MARKUS', 'Markus fødselsdag');
INSERT INTO WISHLIST (LISTID, WISHERNAME,EVENTNAME) VALUES (3, 'AUGUST', 'Augusts juleønsker');


INSERT INTO WISH (WISHID, NAME, DESCRIPTION,ITEMURL,PRICE, LISTID) VALUES (1, 'AirPods Pro', 'De der seje airpods max', 'https://www.apple.com/dk/airpods-2nd-generation/', 4599, 2);
INSERT INTO WISH (WISHID, NAME, DESCRIPTION,ITEMURL,PRICE, LISTID) VALUES (2, 'Oplader', 'En MacBook air oplader', 'https://www.apple.com/dk/shop/mac/accessories/charging-essentials?fh=4595%2B45d4', 199, 1);
INSERT INTO WISH (WISHID, NAME, DESCRIPTION,ITEMURL,PRICE, LISTID) VALUES (3, 'Rolex', 'Et datejust', 'https://www.chrono24.dk/rolex/datejust--mod45.htm', 45999, 3);

INSERT INTO IMAGE(IMAGEID,IMAGE,WISHID) VALUES (1, 'https://www.elgiganten.dk/image/dv_web_D1800010021625045/673048/apple-airpods-pro-2nd-gen-2023-tradlose-horetelefoner-usb-c.jpg',1);
INSERT INTO IMAGE(IMAGEID,IMAGE,WISHID) VALUES (2, 'https://shop0254.sfstatic.io/upload_dir/shop/_thumbs/Apple-Macbook-magsafe-oplader-96W-Usb-C-kompatibel.w250.h250.fill.webp',2);
INSERT INTO IMAGE(IMAGEID,IMAGE,WISHID) VALUES (3, 'https://media3.urvaerket.dk/images/product-popup-o/qr/rolex-submariner-126610LN-0001.jpg',3);