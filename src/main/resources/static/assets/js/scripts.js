/*!
* Start Bootstrap - Creative v7.0.7 (https://startbootstrap.com/theme/creative)
* Copyright 2013-2023 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-creative/blob/master/LICENSE)
*/
//
// Scripts
// 

window.addEventListener('DOMContentLoaded', event => {

    // Navbar shrink function
    var navbarShrink = function () {
        const navbarCollapsible = document.body.querySelector('#mainNav');
        if (!navbarCollapsible) {
            return;
        }
        if (window.scrollY === 0) {
            navbarCollapsible.classList.remove('navbar-shrink')
        } else {
            navbarCollapsible.classList.add('navbar-shrink')
        }

    };

    // Shrink the navbar 
    navbarShrink();

    // Shrink the navbar when page is scrolled
    document.addEventListener('scroll', navbarShrink);

    // Activate Bootstrap scrollspy on the main nav element
    const mainNav = document.body.querySelector('#mainNav');
    if (mainNav) {
        new bootstrap.ScrollSpy(document.body, {
            target: '#mainNav',
            rootMargin: '0px 0px -40%',
        });
    }
    ;

    // Collapse responsive navbar when toggler is visible
    const navbarToggler = document.body.querySelector('.navbar-toggler');
    const responsiveNavItems = [].slice.call(
        document.querySelectorAll('#navbarResponsive .nav-link')
    );
    responsiveNavItems.map(function (responsiveNavItem) {
        responsiveNavItem.addEventListener('click', () => {
            if (window.getComputedStyle(navbarToggler).display !== 'none') {
                navbarToggler.click();
            }
        });
    });

    // Activate SimpleLightbox plugin for portfolio items
    new SimpleLightbox({
        elements: '#portfolio a.portfolio-box'
    });

});









const tempSection = document.querySelector('.temperature');
const placeSection = document.querySelector('.place');
const descSection = document.querySelector('.description');
const iconSection = document.querySelector('.icon');


const API_KEY = '1f3a8cd4fd5f071a885f973292c77ddf';

const button = document.querySelector('.button');


const success = (position) => {
    const latitude = position.coords.latitude;
    const longitude = position.coords.longitude;

    getWeather(latitude, longitude);
}

button.addEventListener('click', () => {
    navigator.geolocation.getCurrentPosition(success, fail);
});



const fail = () => {
    alert("좌표를 받아올 수 없음");
}

// 페이지 로드 시 자동으로 위치 정보를 가져옴
document.addEventListener('DOMContentLoaded', () => {
    navigator.geolocation.getCurrentPosition(success, fail);
});

const getWeather = (lat, lon) => {
    fetch(
        `https://api.openweathermap.org/data/2.5/weather?lat=${lat}&lon=${lon}&appid=${API_KEY}&units=metric&lang=kr`
    )
        .then((response) => response.json())
        .then((json) => {
            // 확인: json 객체가 정상적으로 반환되었는지
            console.log(json);

            // 해당 속성이 정의되어 있는지 확인 후 값을 가져옴
            const temperature = json.main && json.main.temp;
            const place = json.name;
            const description = json.weather[0] && json.weather[0].description;
            const icon = json.weather[0] && json.weather[0].icon;
            const iconURL = icon && `http://openweathermap.org/img/wn/${icon}@2x.png`;

            // 값이 존재할 때만 업데이트
            if (iconURL) {
                iconSection.setAttribute('src', iconURL);
            }
            if (temperature) {
                tempSection.innerText = temperature;
            }
            if (place) {
                placeSection.innerText = place;
            }
            if (description) {
                descSection.innerText = description;
            }
        })
        .catch((error) => {
            alert(error);
        });
}



