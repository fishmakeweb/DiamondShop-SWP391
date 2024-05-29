/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}",
  ],
  theme: {
    extend: {
      fontFamily: {
        cormorant: ['Cormorant SC', 'serif'],
        karla: ['Karla', 'sans-serif']
      },
      colors: {
        'custom-brown': '#b18165',
        darkgray: '#323232', // Custom dark gray color
        lightgray: '#8A8A8A' // Custom light gray color
      },
      fontSize: {
        base: '16px',
        'custom': '17px' // Custom font size
      }
    },
  },
  plugins: [],

}


