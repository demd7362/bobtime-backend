'use strict';
const MANIFEST = 'flutter-app-manifest';
const TEMP = 'flutter-temp-cache';
const CACHE_NAME = 'flutter-app-cache';

const RESOURCES = {".git/COMMIT_EDITMSG": "eb3d988c4aee2a01c794922896d630a7",
".git/config": "64f67ad87d23e84911ba2ba8b99053e7",
".git/description": "a0a7c3fff21f2aea3cfa1d0316dd816c",
".git/HEAD": "4cf2d64e44205fe628ddd534e1151b58",
".git/hooks/applypatch-msg.sample": "ce562e08d8098926a3862fc6e7905199",
".git/hooks/commit-msg.sample": "579a3c1e12a1e74a98169175fb913012",
".git/hooks/fsmonitor-watchman.sample": "a0b2633a2c8e97501610bd3f73da66fc",
".git/hooks/post-update.sample": "2b7ea5cee3c49ff53d41e00785eb974c",
".git/hooks/pre-applypatch.sample": "054f9ffb8bfe04a599751cc757226dda",
".git/hooks/pre-commit.sample": "305eadbbcd6f6d2567e033ad12aabbc4",
".git/hooks/pre-merge-commit.sample": "39cb268e2a85d436b9eb6f47614c3cbc",
".git/hooks/pre-push.sample": "2c642152299a94e05ea26eae11993b13",
".git/hooks/pre-rebase.sample": "56e45f2bcbc8226d2b4200f7c46371bf",
".git/hooks/pre-receive.sample": "2ad18ec82c20af7b5926ed9cea6aeedd",
".git/hooks/prepare-commit-msg.sample": "2b5c047bdb474555e1787db32b2d2fc5",
".git/hooks/push-to-checkout.sample": "c7ab00c7784efeadad3ae9b228d4b4db",
".git/hooks/sendemail-validate.sample": "4d67df3a8d5c98cb8565c07e42be0b04",
".git/hooks/update.sample": "647ae13c682f7827c22f5fc08a03674e",
".git/index": "76a2666dbaf7963facee640c2291ecde",
".git/info/exclude": "036208b4a1ab4a235d75c181e685e5a3",
".git/logs/HEAD": "4e49bf73527f53342556e78617cca1a1",
".git/logs/refs/heads/master": "4e49bf73527f53342556e78617cca1a1",
".git/logs/refs/remotes/origin/master": "944af6ce0098691456bb6475da05b21f",
".git/objects/00/504260c816855f3b879b0ab340f3527993965a": "e14bc8d4d9a03f3957fd2b0bd58e8a33",
".git/objects/07/74c17c0fa7a7e87e24a6935830998d92b52c75": "cd62ee54b7ceea7b2a7804e69b1d9134",
".git/objects/16/5ce0ddf03a820a38f48cba9aa0c9df9b6e6b79": "71df17c95c3124eada62b59e7dabda78",
".git/objects/1a/ffec9c23f03603063f70de9b6537caf0ca988e": "4afa529377ae8bceb1de53b3a032a143",
".git/objects/1c/99631365fa2d3b9dea9817eaa101a9b62ec609": "b0ebd4b587cedb16b1673aa75234ced9",
".git/objects/1f/45b5bcaac804825befd9117111e700e8fcb782": "7a9d811fd6ce7c7455466153561fb479",
".git/objects/20/1afe538261bd7f9a38bed0524669398070d046": "82a4d6c731c1d8cdc48bce3ab3c11172",
".git/objects/25/8b3eee70f98b2ece403869d9fe41ff8d32b7e1": "05e38b9242f2ece7b4208c191bc7b258",
".git/objects/2b/1df43c9d5dabfdeec89e232744bbfaa1337264": "c83c1c11467bf34c81efbd785585ee0d",
".git/objects/2e/348a6944d5c210438a0331a41e2c62655d40a7": "9247bb884c8dde8e69adbf2c7226f8ba",
".git/objects/2e/b7c52e16c14e63bf61db25f41d9676d9599e5f": "e4861b46f3f06a59972c63c19aebd9b9",
".git/objects/30/3de1fcb97c12c3d0a137827c30e0b3328bd523": "652a0e198ce4c91745ea84a3b3647c51",
".git/objects/33/4c68fcf0fb9fe8c767d02782ff346c60a268a7": "2e9427a322a126d19a48f56f337439ef",
".git/objects/38/dd2e01db3bb0e09f5bb38b21979d06672d9306": "793075e952056f826ea4153c2687d06d",
".git/objects/39/dfa1d9178a70161e3d23286da9888a4ff1a5ca": "7f1c8f9f1f5a7c0a9b817473347ef22b",
".git/objects/46/4ab5882a2234c39b1a4dbad5feba0954478155": "2e52a767dc04391de7b4d0beb32e7fc4",
".git/objects/4a/39079e580dc9be820cba2fae41238c49eaa798": "ada1a19fea32fbb6719120809b9eae60",
".git/objects/4d/139e4b1a93582f17332c105b9db2e50865eed4": "943441673018035ea43b958c0cfc168b",
".git/objects/51/39cd94a0f547548bd04451be3aa4a6c289eda6": "3fcb7bc9d807d936ce195149cc574b29",
".git/objects/5a/23b2f58bf33ed8de4cb1c31dee444db7cb186d": "7b8c7902bfcf7e80a8e029494540a0ac",
".git/objects/5a/7b05e1be311772247124911182fda78fde2cec": "d38bfbb93663df272dc4920186bd1040",
".git/objects/5e/66e178af38dc083a7cac6ab1049e83cd4bdd7a": "4b1d662c92972069edb4b2918c200a18",
".git/objects/60/24823bbbc7fe53015ade32f649cb802ed5db8c": "5caa69b4825f8f1ad827c6331978406d",
".git/objects/62/5060701da90289ace7eec24cbb831c506abfd8": "13cb042e0f826b637caae1a04ec71f50",
".git/objects/64/a278f8fd9563b2409851d8e68d8df3818f947f": "8d7bf8068f5dc030fcdc2cb5731c5530",
".git/objects/64/d23234708db7a88fe5d03003bcf9964a68abfd": "f00ef1de094b8ab2cc353a58f1ee074c",
".git/objects/6f/9cad4c116bc8d72e2497226abb5c05ee64982c": "0d104480d68c1652a53721377a02a882",
".git/objects/71/7117947090611c3967f8681ab1ac0f79bca7fc": "ad4e74c0da46020e04043b5cf7f91098",
".git/objects/71/7809363ed19bdd7e1d78f6e421e40a96bc29e3": "9414a3044cb191cc3f57340f57c3dc93",
".git/objects/79/c2191a14801ac4af539313b9ed8ecdac5d477b": "579d9471ac7c8df23ce8ea6fb4052eed",
".git/objects/7c/74152c881750a44710a585b88a85660ff49188": "24d9007c32112af9754c99bc0d0e1643",
".git/objects/7c/9b6e5e44d32c1fc779438f6ae9e93a481c0443": "8d3d280580f2bbcf05ceded89d964bbd",
".git/objects/82/0ceb5f5e42c94a45d1ad2560ce94f06f0673a5": "ed613e96ae7cdd08883d9b3461d2bb1d",
".git/objects/85/6a39233232244ba2497a38bdd13b2f0db12c82": "eef4643a9711cce94f555ae60fecd388",
".git/objects/88/f2ce32ddc3081b38a96b26939abb1ca74b2955": "bdb44a7fef8205f67a9a3aa055ee08d7",
".git/objects/94/bfb1463ad8331bfd687bc751b8920b133da744": "fd2d8c0d844b234856b36b93f652048f",
".git/objects/95/45a89d8968ec05cc6eeca3d4cd298af022f76d": "90c23c3ebf2475333baa899e49e7f4e4",
".git/objects/95/9385c9ef203f7957792cfb0d666f4c981c345c": "dca64bb6390a6dddcc1c57f20204efa9",
".git/objects/97/dfcd7a19aa1edc8ee70b99520c48a5fa03ed45": "4b771e2c28640a3d228a74d85743b731",
".git/objects/ab/6127b36377b90ac6f824a61129fd72ec914ee9": "eebf86bd71c9f8fcac11e129b3ac2590",
".git/objects/af/742adee0a85dd21ea96cbd84182e30e085d6cf": "aa25b932ec40efacb1efe27e7cf25d82",
".git/objects/b2/4a737c3f1a6dbeb31ea76d0e9a0b636ffd4909": "eb872bdc192b4892dec290000063f428",
".git/objects/b5/0254288cc6319d153c4af1d64870d95ee2436f": "468a6506934a07c970a4739eae75eedd",
".git/objects/ba/5317db6066f0f7cfe94eec93dc654820ce848c": "9b7629bf1180798cf66df4142eb19a4e",
".git/objects/bf/01d0185a0b2d76d5b82d40568977688fee8e41": "3360a488372b726109924af176b7d032",
".git/objects/c4/c5cf8854a725b6eff76c456a419cec4e5245cf": "2db7dcef8835aca151b9dd679c5bbd09",
".git/objects/c5/f4bc2a4da91586f3005813077f0d0aa9040f82": "3191028b787554cee4652f5050144bff",
".git/objects/c6/aec4924ee68d1e311048239088b40f06497a26": "95525da12bf891a0014a5535b2d19fdb",
".git/objects/cd/64d069b41df1a292d127bb9e660ed8ef5f2cae": "0a669981f769b83606b03fb6c7294b21",
".git/objects/ce/6c6829189159f87016515104c789120468c052": "8b2d0d3f2145b6bc48d8d68c3d66a33f",
".git/objects/cf/0742350685fec41ea3488053d19a4ad3460821": "aac70ce7cdcba27f213eb3aa9ca59dfb",
".git/objects/d4/3532a2348cc9c26053ddb5802f0e5d4b8abc05": "3dad9b209346b1723bb2cc68e7e42a44",
".git/objects/d6/01058c3e39b62809bb29e6e844a18832df8df9": "4379bab6b93380d45cdffc7e42faccce",
".git/objects/d9/6058c40dac1ff0a1f6b1ca165c129b94d18860": "f05c0adcc4e3ccdd23baeaaf21cb030b",
".git/objects/df/78b4f26b99b3c6d3cf2ea9055296ae62c56484": "0dde11ee552821575fa35c7c237337ac",
".git/objects/e0/8cc814ea6787201766c240cf3529af0482da1a": "0af9c288413dbd201c100f57facdbd3b",
".git/objects/e5/3dea955d1df5e3e710ad17907daf6ee23f8bba": "a28b6a544269d0e66f88adeef65a6e78",
".git/objects/e8/2c5850db3a3482d0c954a4dc122c02de555ce7": "d357cd906b3805bf81477f5527cca086",
".git/objects/f1/48dfe12285ec1045843f2a51ffa51fc0027556": "d85730cf449c980d01d7b2919e15f1f5",
".git/objects/f2/04823a42f2d890f945f70d88b8e2d921c6ae26": "6b47f314ffc35cf6a1ced3208ecc857d",
".git/objects/fb/7757817f5464aa0c6aac24d96945542525042f": "20440bf91c2bcce7c2e6cba310dc7dbe",
".git/objects/fc/247631ff7a6866a09e4d3ad847f1a773511e26": "86d45b4708790409346dcf5734a55cdb",
".git/refs/heads/master": "71ec8890e7be771fc7f2c4c96ce4b34d",
".git/refs/remotes/origin/master": "71ec8890e7be771fc7f2c4c96ce4b34d",
"assets/AssetManifest.bin": "7d546df031984e66a55f4a8595cd04e7",
"assets/AssetManifest.bin.json": "522e1d01b6bb3032c61ebcfc9e4ec6c8",
"assets/AssetManifest.json": "c1f877621a00393b90e2b90cb28f3f2e",
"assets/assets/logo.png": "e96862627094b24afcb74e77ced4abfc",
"assets/assets/splash_image.png": "d2495fbd6e78302a4eae31ec70b54037",
"assets/FontManifest.json": "dc3d03800ccca4601324923c0b1d6d57",
"assets/fonts/MaterialIcons-Regular.otf": "63f436f40b1e843242ed47d357746b83",
"assets/NOTICES": "a9c8ccde65f162c89d55906fc88f3605",
"assets/packages/cupertino_icons/assets/CupertinoIcons.ttf": "e986ebe42ef785b27164c36a9abc7818",
"assets/shaders/ink_sparkle.frag": "ecc85a2e95f5e9f53123dcaf8cb9b6ce",
"canvaskit/canvaskit.js": "c86fbd9e7b17accae76e5ad116583dc4",
"canvaskit/canvaskit.js.symbols": "38cba9233b92472a36ff011dc21c2c9f",
"canvaskit/canvaskit.wasm": "3d2a2d663e8c5111ac61a46367f751ac",
"canvaskit/chromium/canvaskit.js": "43787ac5098c648979c27c13c6f804c3",
"canvaskit/chromium/canvaskit.js.symbols": "4525682ef039faeb11f24f37436dca06",
"canvaskit/chromium/canvaskit.wasm": "f5934e694f12929ed56a671617acd254",
"canvaskit/skwasm.js": "445e9e400085faead4493be2224d95aa",
"canvaskit/skwasm.js.symbols": "741d50ffba71f89345996b0aa8426af8",
"canvaskit/skwasm.wasm": "e42815763c5d05bba43f9d0337fa7d84",
"canvaskit/skwasm.worker.js": "bfb704a6c714a75da9ef320991e88b03",
"flutter.js": "c71a09214cb6f5f8996a531350400a9a",
"index.html": "52bef84962c4edecae8828bf6ca11075",
"/": "52bef84962c4edecae8828bf6ca11075",
"main.dart.js": "9a95f62f947692124a2010bb19761e67",
"splash/img/dark-1x.png": "c209cbd7156910214817a9d21471ba83",
"splash/img/dark-2x.png": "59b930aef11e03e9c4d2b1e1b20b5e0e",
"splash/img/dark-3x.png": "e2bfb95e7bc4c0c2d4bc8754a9c6a5f6",
"splash/img/dark-4x.png": "b46619064ad0dafba6b87699c4b80bb1",
"splash/img/light-1x.png": "c209cbd7156910214817a9d21471ba83",
"splash/img/light-2x.png": "59b930aef11e03e9c4d2b1e1b20b5e0e",
"splash/img/light-3x.png": "e2bfb95e7bc4c0c2d4bc8754a9c6a5f6",
"splash/img/light-4x.png": "b46619064ad0dafba6b87699c4b80bb1",
"version.json": "e93101c883f9282a55352b68267cd8aa"};
// The application shell files that are downloaded before a service worker can
// start.
const CORE = ["main.dart.js",
"index.html",
"assets/AssetManifest.bin.json",
"assets/FontManifest.json"];

// During install, the TEMP cache is populated with the application shell files.
self.addEventListener("install", (event) => {
  self.skipWaiting();
  return event.waitUntil(
    caches.open(TEMP).then((cache) => {
      return cache.addAll(
        CORE.map((value) => new Request(value, {'cache': 'reload'})));
    })
  );
});
// During activate, the cache is populated with the temp files downloaded in
// install. If this service worker is upgrading from one with a saved
// MANIFEST, then use this to retain unchanged resource files.
self.addEventListener("activate", function(event) {
  return event.waitUntil(async function() {
    try {
      var contentCache = await caches.open(CACHE_NAME);
      var tempCache = await caches.open(TEMP);
      var manifestCache = await caches.open(MANIFEST);
      var manifest = await manifestCache.match('manifest');
      // When there is no prior manifest, clear the entire cache.
      if (!manifest) {
        await caches.delete(CACHE_NAME);
        contentCache = await caches.open(CACHE_NAME);
        for (var request of await tempCache.keys()) {
          var response = await tempCache.match(request);
          await contentCache.put(request, response);
        }
        await caches.delete(TEMP);
        // Save the manifest to make future upgrades efficient.
        await manifestCache.put('manifest', new Response(JSON.stringify(RESOURCES)));
        // Claim client to enable caching on first launch
        self.clients.claim();
        return;
      }
      var oldManifest = await manifest.json();
      var origin = self.location.origin;
      for (var request of await contentCache.keys()) {
        var key = request.url.substring(origin.length + 1);
        if (key == "") {
          key = "/";
        }
        // If a resource from the old manifest is not in the new cache, or if
        // the MD5 sum has changed, delete it. Otherwise the resource is left
        // in the cache and can be reused by the new service worker.
        if (!RESOURCES[key] || RESOURCES[key] != oldManifest[key]) {
          await contentCache.delete(request);
        }
      }
      // Populate the cache with the app shell TEMP files, potentially overwriting
      // cache files preserved above.
      for (var request of await tempCache.keys()) {
        var response = await tempCache.match(request);
        await contentCache.put(request, response);
      }
      await caches.delete(TEMP);
      // Save the manifest to make future upgrades efficient.
      await manifestCache.put('manifest', new Response(JSON.stringify(RESOURCES)));
      // Claim client to enable caching on first launch
      self.clients.claim();
      return;
    } catch (err) {
      // On an unhandled exception the state of the cache cannot be guaranteed.
      console.error('Failed to upgrade service worker: ' + err);
      await caches.delete(CACHE_NAME);
      await caches.delete(TEMP);
      await caches.delete(MANIFEST);
    }
  }());
});
// The fetch handler redirects requests for RESOURCE files to the service
// worker cache.
self.addEventListener("fetch", (event) => {
  if (event.request.method !== 'GET') {
    return;
  }
  var origin = self.location.origin;
  var key = event.request.url.substring(origin.length + 1);
  // Redirect URLs to the index.html
  if (key.indexOf('?v=') != -1) {
    key = key.split('?v=')[0];
  }
  if (event.request.url == origin || event.request.url.startsWith(origin + '/#') || key == '') {
    key = '/';
  }
  // If the URL is not the RESOURCE list then return to signal that the
  // browser should take over.
  if (!RESOURCES[key]) {
    return;
  }
  // If the URL is the index.html, perform an online-first request.
  if (key == '/') {
    return onlineFirst(event);
  }
  event.respondWith(caches.open(CACHE_NAME)
    .then((cache) =>  {
      return cache.match(event.request).then((response) => {
        // Either respond with the cached resource, or perform a fetch and
        // lazily populate the cache only if the resource was successfully fetched.
        return response || fetch(event.request).then((response) => {
          if (response && Boolean(response.ok)) {
            cache.put(event.request, response.clone());
          }
          return response;
        });
      })
    })
  );
});
self.addEventListener('message', (event) => {
  // SkipWaiting can be used to immediately activate a waiting service worker.
  // This will also require a page refresh triggered by the main worker.
  if (event.data === 'skipWaiting') {
    self.skipWaiting();
    return;
  }
  if (event.data === 'downloadOffline') {
    downloadOffline();
    return;
  }
});
// Download offline will check the RESOURCES for all files not in the cache
// and populate them.
async function downloadOffline() {
  var resources = [];
  var contentCache = await caches.open(CACHE_NAME);
  var currentContent = {};
  for (var request of await contentCache.keys()) {
    var key = request.url.substring(origin.length + 1);
    if (key == "") {
      key = "/";
    }
    currentContent[key] = true;
  }
  for (var resourceKey of Object.keys(RESOURCES)) {
    if (!currentContent[resourceKey]) {
      resources.push(resourceKey);
    }
  }
  return contentCache.addAll(resources);
}
// Attempt to download the resource online before falling back to
// the offline cache.
function onlineFirst(event) {
  return event.respondWith(
    fetch(event.request).then((response) => {
      return caches.open(CACHE_NAME).then((cache) => {
        cache.put(event.request, response.clone());
        return response;
      });
    }).catch((error) => {
      return caches.open(CACHE_NAME).then((cache) => {
        return cache.match(event.request).then((response) => {
          if (response != null) {
            return response;
          }
          throw error;
        });
      });
    })
  );
}
