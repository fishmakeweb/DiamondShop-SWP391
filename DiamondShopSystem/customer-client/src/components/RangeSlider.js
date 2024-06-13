import React from 'react';
import PropTypes from 'prop-types';
import '../style/RangeSlider.css'; // Make sure to create the CSS file for styling

const RangeSlider = ({ label, grades, minGrade, maxGrade, setMinGrade, setMaxGrade }) => {
  const handleMinChange = (event) => {
    const value = Math.min(parseInt(event.target.value), maxGrade - 1);
    setMinGrade(value);
  };

  const handleMaxChange = (event) => {
    const value = Math.max(parseInt(event.target.value), minGrade + 1);
    setMaxGrade(value);
  };

  const maxIndex = grades.length - 1;

  return (
    <div className="relative max-w-xl w-full mb-10">
       <div className="flex justify-between mb-2">
        <label className="label">{label}</label>
        </div>
      <input 
        type="range"
        step="1"
        min="0"
        max={maxIndex}
        value={minGrade}
        onChange={handleMinChange}
        className="absolute pointer-events-none appearance-none z-20 h-2 w-full opacity-0 cursor-pointer"
      />
      <input 
        type="range"
        step="1"
        min="0"
        max={maxIndex}
        value={maxGrade}
        onChange={handleMaxChange}
        className="absolute pointer-events-none appearance-none z-20 h-2 w-full opacity-0 cursor-pointer"
      />
      <div className="relative z-10 h-2">
        <div className="absolute z-10 left-0 right-0 bottom-0 top-0 rounded-md bg-gray-200"></div>
        <div className="absolute z-20 top-0 bottom-0 rounded-md bg-brown-300" style={{ right: `${100 - (maxGrade / maxIndex) * 100}%`, left: `${(minGrade / maxIndex) * 100}%` }}></div>
        <div className="absolute z-30 w-6 h-6 top-0 left-0 bg-brown-300 rounded-full -mt-2 -ml-1" style={{ left: `${(minGrade / maxIndex) * 100}%` }}></div>
        <div className="absolute z-30 w-6 h-6 top-0 right-0 bg-brown-300 rounded-full -mt-2 -mr-3" style={{ right: `${100 - (maxGrade / maxIndex) * 100}%` }}></div>
      </div>
      <div className="flex justify-between mt-2">
        {grades.map((grade, index) => (
          <span key={index} className="text-xs">{grade}</span>
        ))}
      </div>
    </div>
  );
};

RangeSlider.propTypes = {
  label: PropTypes.string.isRequired,
  grades: PropTypes.arrayOf(PropTypes.string).isRequired,
  minGrade: PropTypes.number.isRequired,
  maxGrade: PropTypes.number.isRequired,
  setMinGrade: PropTypes.func.isRequired,
  setMaxGrade: PropTypes.func.isRequired,
};

export default RangeSlider;
