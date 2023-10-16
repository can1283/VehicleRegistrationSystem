import React, { useState } from 'react';
import axios from 'axios';
import {Link, useNavigate} from 'react-router-dom';
import { Formik, Field, Form, ErrorMessage } from 'formik';
import * as Yup from 'yup';

const AddVehicleForm = () => {
    const navigation = useNavigate();
    const [errorMessage, setErrorMessage] = useState('');

    const validationSchema = Yup.object().shape({
        name: Yup.string()
            .min(2, 'Name size must be between 2 and 30 characters')
            .max(30, 'Name size must be between 2 and 30 characters')
            .required('Name is required'),
        brand: Yup.string()
            .min(1, 'Brand size must be between 1 and 16 characters')
            .max(16, 'Brand size must be between 1 and 16 characters')
            .required('Brand is required'),
        model: Yup.string()
            .min(1, 'Model size must be between 1 and 16 characters')
            .max(16, 'Model size must be between 1 and 16 characters')
            .required('Model is required'),
        modelYear: Yup.number().required('Model year is required'),
        plateCode: Yup.string()
            .matches(
                /^(?=.{1,8}$)([0-9]{2}|[01]{2})([A-Za-z]{2,})([0-9]{2,})$/,
                'Plate code is not valid'
            )
            .min(6, 'Plate code size must be between 6 and 8 characters')
            .max(8, 'Plate code size must be between 6 and 8 characters')
            .required('Plate code is required'),
        active: Yup.boolean().required('Active status is required'),
    });

    return (
        <>
        <div className="bg-gradient-to-r from-cyan-600 to-blue-500 w-full h-full absolute"></div>
    <div className="absolute flex align-items-center justify-center w-full h-full">
        <div className="bg-white w-full max-w-md mx-auto shadow-2xl border border-2 rounded-2xl px-10 py-8">
            <h2 className="text-2xl font-bold mb-4">Add New Vehicle</h2>
            <Formik
                initialValues={{
                    name: '',
                    brand: '',
                    model: '',
                    modelYear: '',
                    plateCode: '',
                    active: true,
                }}
                validationSchema={validationSchema}
                onSubmit={async (values, { setSubmitting, setErrors }) => {
                    try {
                        const userId = localStorage.getItem('id');
                        await axios.post(`http://localhost:8080/api/vehicles/${userId}`, values);
                        navigation('/dashboard');
                    } catch (error) {
                        setErrorMessage(error.response.data.plateCode)
                    }
                    setSubmitting(false);
                }}
            >
                <Form>
                    <div>
                        <label className="block font-medium">Vehicle Name:</label>
                        <Field
                            type="text"
                            name="name"
                            className="p-2 border border-gray-100 rounded-xl outline-0 w-full"
                        />
                        <ErrorMessage name="name" component="div" className="text-red-500" />
                    </div>
                    <div>
                        <label className="block font-medium mt-4">Brand:</label>
                        <Field
                            type="text"
                            name="brand"
                            className="p-2 border border-gray-100 rounded-xl outline-0 w-full"
                        />
                        <ErrorMessage name="brand" component="div" className="text-red-500" />
                    </div>
                    <div>
                        <label className="block font-medium mt-4">Model:</label>
                        <Field
                            type="text"
                            name="model"
                            className="p-2 border border-gray-100 rounded-xl outline-0 w-full"
                        />
                        <ErrorMessage name="model" component="div" className="text-red-500" />
                    </div>
                    <div>
                        <label className="block font-medium mt-4">Model Year:</label>
                        <Field
                            type="text"
                            name="modelYear"
                            placeholder="2023"
                            className="p-2 border border-gray-100 rounded-xl outline-0 w-full"
                        />
                        <ErrorMessage name="modelYear" component="div" className="text-red-500" />
                    </div>
                    <div>
                        <label className="block font-medium mt-4">Plate Code:</label>
                        <Field
                            type="text"
                            name="plateCode"
                            className="p-2 border border-gray-100 rounded-xl outline-0 w-full"
                        />
                        <ErrorMessage name="plateCode" component="div" className="text-red-500" />
                    </div>
                    <div>
                        <label className="inline-block font-medium mt-4 mb-4 mr-2">Active:</label>
                        <Field type="checkbox" name="active" />
                    </div>
                    <div className={"flex justify-between gap-2"}>
                        <button
                            type="submit"
                            className="bg-indigo-700 p-2 rounded-xl text-m text-cyan-50 shadow-sm outline-0 w-75"
                        >
                            Add Vehicle
                        </button>
                        <Link to={"/dashboard"}>
                            <button className=" bg-indigo-500 text-white rounded-xl w-32 p-2">Back
                            </button>
                        </Link>
                        {errorMessage && (
                            <div className="text-red-500 mt-4">{errorMessage}</div>
                        )}
                    </div>
                </Form>
            </Formik>
        </div>
    </div>
        </>
);
};

export default AddVehicleForm;
